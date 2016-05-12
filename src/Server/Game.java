package Server;

import java.net.InetAddress;
import java.util.ArrayList;

import bombelogik.BombeStart;

public class Game {

	int id;
	ArrayList<NetworkPlayer> players;
	BombeStart game;

	public Game(ArrayList<NetworkPlayer> players, int id) {
		//this.game = game;
		
		game = new BombeStart().start();
		this.players = players;
		this.id = id;
	}

	public String getSendableData() {
		String toSend = game.getSendableData();
		return toSend;
	}
	
	public ArrayList<NetworkPlayer> getNetworkPlayers(){
		return players;
	}
	
	public boolean isPlayerInGame(InetAddress address){
		for(NetworkPlayer player: players){
			if(player.getIpAddress() == address){
				return true;
			}
		}
		return false;
	}

}
