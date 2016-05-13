package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import packets.Packet;

public class ServerPacketSender implements Runnable {

	private DatagramSocket socket;
	private byte[] data;
	
	private NetworkPlayer player = null;
	private ArrayList<NetworkPlayer> players = null;
	
	public ServerPacketSender(DatagramSocket socket, byte[] data, NetworkPlayer player){
		this.socket = socket;
		this.data = data;
		this.player = player;
	}
	
	public ServerPacketSender(DatagramSocket socket, byte[] data, ArrayList<NetworkPlayer> players){
		this.socket = socket;
		this.data = data;
		this.players = players;
		System.out.println("Serverpacketsender created");
	}
	
	@Override
	public void run() {
		System.out.println("ruuun!");
		readySendPacket();
	}
	
	private void readySendPacket(){
		if(!(player == null)){
			sendDataToClient();
		}
		else if(!(players == null)){
			System.out.println("Sending data to all clients");
			sendDataToAllClients();
		}
	}
	
	 private void sendData(byte[] data, InetAddress address, int port) {
         DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
         String send = new String(data, 0, data.length);
          try {
        	  System.out.println("sending this packet: " + send);
              socket.send(packet);
              System.out.println("Packet send");
          } catch (IOException e) {
              e.printStackTrace();
          }
	 }

	private void sendDataToClient(){
		sendData(data, player.getIpAddress(), player.getPort());
	}
	
	private void sendDataToAllClients() {
		System.out.println(players.size());
        for (NetworkPlayer player : players) {
        	System.out.println(players.size());
        	System.out.println(player.getIpAddress() + " " + player.getPort());
            sendData(data, player.getIpAddress(), player.getPort());
        }
    }
	
}
