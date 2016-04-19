package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ConnectionListener implements Runnable {

	int port = 1234;
	int backLog = 120;
	ServerSocket serverSocket;
	InetAddress hostAddress;
	SocketAddress socketAddress;
	Socket clientSocket;
	ArrayList<NetworkPlayer> players = new ArrayList<NetworkPlayer>();
	
	String host = "10.16.241.186";
	TCPConnector tcpConnect;
	
	public ConnectionListener() {
		// Attempt to get the host address
		try {
			hostAddress = InetAddress.getByName(null);
			
		} catch (UnknownHostException e) {
			System.out.println("Could not get the host address.");
			return;
		}
		// Announce the host address
		System.out.println("Server host address is: " + hostAddress);
		// Attempt to create server socket
		try {
			serverSocket = new ServerSocket(port, backLog, hostAddress);
		} catch (IOException e) {
			System.out.println("Could not open server socket.");
			return;
		}
		// Announce the socket creation
		System.out.println("Socket " + serverSocket + " created.");

	}

	@Override
	public void run() {
		
		while (true) {
			
			//Remove disconnected players
			for (int i = 0; i < players.size(); i++) {
				if (!players.get(i).isConnected()) {
					// destroy thread first then remove player from list
					players.get(i).closePlayer();
					players.remove(i);
				}
			}
			//Listen for new client and add to the list
			/*try {
				tcpConnect = new TCPConnector(hostAddress.toString(), port);
				tcpConnect.connect();
				System.out.println(tcpConnect);
			} catch (Exception e){
				e.printStackTrace();
			}*/
		//}
				clientSocket = new Socket();
				try {
					Thread.sleep(200);
					System.out.println(clientSocket);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} try{
				clientSocket = serverSocket.accept();
				System.out.println("Client socket accept Method has run " + clientSocket.isConnected());
				//Thread.sleep(200);
				System.out.println("Client socket closed?" + clientSocket.isClosed());
				NetworkPlayer player = new NetworkPlayer(clientSocket);
				playerGameQueue(player);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}/* catch (InterruptedException e) {
				System.out.println("Thread stopped");
				e.printStackTrace();
			}*/
		}	
	}

		

	

	private void playerGameQueue(NetworkPlayer player) {
		players.add(player);
		//int size = players.size();
	}

}
