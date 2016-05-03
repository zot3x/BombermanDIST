package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Server.Bomberman.Map;

public class NetworkPlayer {

	private Socket socket;
	private boolean connected;
	private ObjectInputStream objectInput;

	public NetworkPlayer(Socket socket) {
		System.out.println("Network player socket is closed?" + socket.isClosed());
		this.socket = socket;

		System.out.println("Network player socket is closed?" + socket.isClosed());

		Runnable runnable = new Runnable() {
			public void run() {
				try {
					objectInput = new ObjectInputStream(socket.getInputStream());
				} catch (IOException e) {
					System.out.println("Could not get input stream from "
							+ toString());
					e.printStackTrace();
					return;
				}

				System.out.println(socket + " has connected.");

				connected = true;

				while (true) {
					
				}
			}
		};
		new Thread(runnable).start();
	}
	
	

	private void updateGameState(Map map) {
		int[][] grid = map.getGrid();

		for (int i = 0; i < (map.getHeight()); i++) {
			for (int j = 0; j < (map.getWidth()); j++) {
				System.out.print(grid[i][j] + " ");
				if (j == map.getWidth() - 1) {
					System.out.println();
				}
			}
		}

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public void closePlayer() {
		try {
			connected = false;
			socket.close();
		} catch (IOException e) {
			System.out.println("Could not close" + socket);
		}
	}

	public boolean isConnected() {
		return connected;
	}

	public String toString() {
		return new String(socket.toString());
	}

}
