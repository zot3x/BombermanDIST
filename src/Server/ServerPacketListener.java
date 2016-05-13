package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import packets.PacketBomb;
import packets.PacketConnect;
import packets.PacketDisconnect;
import packets.PacketMove;
public class ServerPacketListener implements Runnable {

	private int port = 12346;
	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] buffer;
	private InetAddress hostAddress;
	private static ArrayList<NetworkPlayer> players = new ArrayList<NetworkPlayer>();
	private static ArrayList<NetworkPlayer> newPlayers = new ArrayList<NetworkPlayer>();
	private static ArrayList<Game> gamesRunning = new ArrayList<Game>();
	private static int gameID = 0;
	
	
	public ServerPacketListener() {
		// Attempt to get the host address
		try {
			hostAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.out.println("Could not get the host address.");
			return;
		}
		// Announce the host address
		System.out.println("Server host address is: " + hostAddress);
		// Attempt to create server socket
		try {
			socket = new DatagramSocket(port, hostAddress);
		} catch (IOException e) {
			System.out.println("Could not open server socket.");
			return;
		}
		// Announce the socket creation
		System.out.println("Socket " + socket + " created.");

	}

	@Override
	public void run() {
		
		buffer = new byte[512];
		packet = new DatagramPacket(buffer, buffer.length);
		
		while (true) {
			
			/*UDP*/
			try{
			socket.receive(packet);
			new Thread(new ServerPacketHandler(socket, packet)).start();
			} catch(IOException e){
				System.out.println("Error receiving incoming packet");
				e.printStackTrace();
			}
			
			//Handle players disconnected with no disconnect packet send
			
			/*UDP*/
		/* TCP	
			//Remove disconnected players
			for (int i = 0; i < players.size(); i++) {
				if (!players.get(i).isConnected()) {
					// destroy thread first then remove player from list
					players.get(i).closePlayer();
					players.remove(i);
				}
			}
*/
		}

	}

	  public static int[] addConnection(NetworkPlayer player) {
		  int data[] = new int[2];
		  newPlayers.add(player);
		  data[0] = newPlayers.size();
		  data[1] = gameID;
		  if(newPlayers.size() == 4){
			  startNewGame();
		  }
		  return data;
	    }

	    public static void removeConnection(PacketDisconnect packet) {
	    	removeNetworkPlayer(packet.getAddress());
	    }
	    
	    public static void removeNetworkPlayer(InetAddress ip){
	    	for (NetworkPlayer player : players) {
	    		if(player.getIpAddress() == ip){
	    			players.remove(player);
	    			break;
	    		}
	    	}
	    	for(NetworkPlayer player : newPlayers){
	    		if(player.getIpAddress() == ip){
	    			newPlayers.remove(player);
	    			break;
	    		}
	    	}
	    }

	    public static NetworkPlayer getNetworkPlayer(InetAddress ip) {
	        for (NetworkPlayer player : players) {
	            if (player.getIpAddress() == ip) {
	                return player;
	            }
	        }
	        return null;
	    }
	    
	    public static void handleMove(PacketMove packet, InetAddress address) {
	    	Game game = findGame(packet.getGameID());
	    	if(!(game == null)){
	    		int playerID = packet.getID();
	    		int direction = packet.getDirection();
	    		game.MovePlayerOrSetBomb(playerID, direction);
	    	}   	
	    }
	    
	    public static void handleBomb(PacketBomb packet, InetAddress address) {
	    	Game game = findGame(packet.getGameID());
	    	if(!(game == null)){
	    		int playerID = packet.getPlayerID();
	    		int direction = packet.getDirection();
	    		game.MovePlayerOrSetBomb(playerID, direction);
	    	}
	    }
	    
	    private static Game findGame(int gameID){
	    	for(Game game : gamesRunning){
	    		if(game.getId() == gameID){
	    			return game;
	    		}
	    	}
			return null;
	    }

	    public static void startNewGame(){
	    Game game = new Game(newPlayers, gameID);
	    gamesRunning.add(game);
	    newPlayers.removeAll(newPlayers);
	    gameID++;
	   // byte[] data = 111;
	   // new ServerPacketSender(socket, data, game.getNetworkPlayers());
	    }
	    
	    public static void removeGame(int gameID){
	    	for(Game game: gamesRunning){
	    		if(game.getId() == gameID){
	    			gamesRunning.remove(game);
	    		}
	    	}
	    }
}
