package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import packets.Packet;


public class ClientPacketSender implements Runnable{
	
		private DatagramSocket socket;
		private byte[] data;
		private int port;
		private InetAddress address;
		
		ClientPacketSender(DatagramSocket socket, byte[] data){
			this.socket = socket;
			this.data = data;
			this.address = socket.getInetAddress();
			this.port = socket.getPort();
			
			}
		
		public void run() {
			readySendPacket();
		}
		
		private void readySendPacket(){
				sendDataToServer();
		}
		
		 private void sendData(byte[] data) {
	         DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
	          try {
	              socket.send(packet);
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
		 }

		private void sendDataToServer(){
			sendData(data);
		}
}


