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
	}
	
	
	public void run() {
		readySendPacket();
	}
	
	private void readySendPacket(){
		if(!(player == null)){
			sendDataToClient();
		}
		else if(!(players == null)){
			sendDataToAllClients();
		}
	}
	
	 private void sendData(byte[] data, InetAddress address, int port) {
         DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
          try {
              socket.send(packet);
          } catch (IOException e) {
              e.printStackTrace();
          }
	 }

	private void sendDataToClient(){
		sendData(data, player.getIpAddress(), player.getPort());
	}
	
	private void sendDataToAllClients() {
        for (NetworkPlayer player : players) {
            sendData(data, player.getIpAddress(), player.getPort());
        }
    }
	
}
