package Server;

import java.net.InetAddress;

public class NetworkPlayer {

	private boolean connected;
	private InetAddress ipAddress;
	private int port;
	
	
	public NetworkPlayer(InetAddress ipAddress, int port) {
            this.ipAddress = ipAddress;
            this.port = port;
            connected = true;
	}

	public void closePlayer() {
		connected = false;
	}

	public boolean isConnected() {
		return connected;
	}
	
	public InetAddress getIpAddress(){
		return ipAddress;
	}
	
	public int getPort(){
		return port;
	}
}
