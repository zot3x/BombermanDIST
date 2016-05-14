package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import packets.*;
import packets.Packet.Packets;
import Server.ServerPacketListener;

public class ServerPacketHandler implements Runnable {

	private DatagramSocket socket;
	private DatagramPacket packet;
	private boolean response = false;
	byte[] data = null;
	String dataString = null;
	private Packet packetToHandle = null;

	private InetAddress address;
	private int port;

	public ServerPacketHandler(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
		this.data = packet.getData();
		this.dataString = new String(data, 0, data.length);
		address = packet.getAddress();
		port = packet.getPort();
	}

	public void run() {
		handlePacket();
		if (response) {
			DatagramPacket response = new DatagramPacket(data, data.length,
					packet.getAddress(), packet.getPort());
			try {
				socket.send(response);
			} catch (IOException e) {
				System.out.println("Error sending server response packet to : "
						+ packet.getAddress() + "on port " + packet.getPort());
				e.printStackTrace();
			}
		}
	}

	private void handlePacket() {
	//	for(int i = 0; i < data.length; i++){
	//		System.out.println(data[i]);
		//}
		String message = new String(data);
		System.out.println("PACKET RECEIVED ON SERVER : " + message);
		
		System.out.println(data.length + " " + data[0] + " " + data[50]);
		System.out.println("String = " + dataString);
		Packets type = Packet.checkPacketID(dataString.substring(0, 1));
		System.out.println("Type = " + type);
		switch (type) {
		default:
		case INVALID:
			break;
		case CONNECT:
			System.out.println("Inside CONNECT SWITCH");
			PacketClientConnect packetConnect = new PacketClientConnect(data);
			System.out.println("Hallo!!" + packetConnect.getPacketID());
			System.out.println("Packet with packetID: " + packetConnect.getPacketID() + " from IP: " + address + " on port: " + port + " has been handled...");
			NetworkPlayer player = new NetworkPlayer(address, port);
			System.out.println(player.getIpAddress() + " " + player.getPort());
			int[] responseData = ServerPacketListener.addConnection(player);
			System.out.println("player added");
//			Packet responsePacket = new PacketConnect(responseData[0], responseData[1]);
	//		new ServerPacketSender(responsePacket,);
			break;
		case DISCONNECT:
			PacketDisconnect packetDisconnect = new PacketDisconnect(data);
			System.out.println("Player " + address + ":" + port + "] "
					+ packet.getAddress() + " has left...");
			ServerPacketListener
					.removeConnection((PacketDisconnect) packetToHandle);
			break;
		case MOVE:
			PacketMove packetMove = new PacketMove(data);
			ServerPacketListener.handleMove(packetMove, address);
		case BOMB:
			PacketBomb packetBomb = new PacketBomb(data);
			ServerPacketListener.handleBomb(packetBomb, address);
		case READY:
		}
	}
}
