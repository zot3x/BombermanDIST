package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import packets.Packet;


public class ClientPacketSender{
	
		private DatagramSocket socket;
		private byte[] data;
		private int port = 12346;
		private InetAddress address;
		
		public ClientPacketSender(DatagramSocket socket, byte[] data){
			this.socket = socket;
			this.data = data;
			try {
				this.address = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   Thread thread = new Thread(new Runnable() {
					public void run() {
						readySendPacket();
					}
					});
		        thread.start();
			}
		
		ClientPacketSender(int id, int gameID, int keycode){
			
		}
		/*
		public void run() {
			readySendPacket();
		}
		*/
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


