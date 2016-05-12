package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Server.NetworkPlayer;
import Server.ServerPacketListener;
import packets.*;
import packets.Packet.Packets;

public class ClientPacketHandler implements Runnable{

	private DatagramSocket socket;
	private DatagramPacket packet;
	private boolean response = false;
	byte[] data = null;
	private Packet packetToHandle;
	
	private InetAddress address;
	private int port;
	
	public ClientPacketHandler(DatagramSocket socket, DatagramPacket packet){
		this.socket = socket;
		this.packet = packet;
		this.address = socket.getInetAddress();
		this.port = socket.getPort();
	}
	
	public void run() {
		handlePacket();
		if(response){
			DatagramPacket response = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
			try {
				socket.send(response);
			} catch (IOException e) {
				System.out.println("Error sending client response packet to : " + packet.getAddress() + "on port " + packet.getPort());
				e.printStackTrace();
			}
		}
	}
	
	private void handlePacket(){
		String message = new String(data);
		Packets type = Packet.checkPacketID(message.substring(0, 2));
		switch (type) {
		default:
		case INVALID:
			break;
		case CONNECT:
			packetToHandle = new PacketConnect(data);
			System.out.println("[" + address + ":" + port + "] "
					+ packet.getAddress() + " has connected...");
			NetworkPlayer player = new NetworkPlayer(address, port);
		//	ClientPacketListener.addConnection(player,
			//		(PacketConnect) packetToHandle);
			break;
		case GAMESTATE:
			packetToHandle = new PacketGameState(data);
			ClientPacketListener.handleGameState((PacketGameState) packetToHandle);
			
		case READY:
		}
	}
	
	public void handleGameState(PacketGameState gameState){
    	//do stuff
    }
}