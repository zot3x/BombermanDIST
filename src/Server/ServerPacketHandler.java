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
	private Packet packetToHandle = null;

	private InetAddress address;
	private int port;

	public ServerPacketHandler(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
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
			//ServerPacketListener.addConnection(player,
			//		(PacketConnect) packetToHandle);
			int id = ServerPacketListener.addConnection(player);
			packetToHandle.setID = 
			break;
		case DISCONNECT:
			packetToHandle = new PacketDisconnect(data);
			System.out.println("[" + address + ":" + port + "] "
					+ packet.getAddress() + " has left...");
			ServerPacketListener
					.removeConnection((PacketDisconnect) packetToHandle);
			break;
		case MOVE:
			packetToHandle = new PacketMove(data);
			ServerPacketListener.handleMove((PacketMove) packetToHandle);
		case BOMB:
			packetToHandle = new PacketBomb(data);
			ServerPacketListener.handleBomb((PacketBomb) packetToHandle);
		case READY:
		}
	}
}
