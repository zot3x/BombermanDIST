package Server;

import java.net.InetAddress;
import java.util.ArrayList;

import ServerBombeLogik.Accounts;
import ServerBombeLogik.BombeStart;

public class Game {

	int id;
	ArrayList<NetworkPlayer> players;
	BombeStart game;

	public Game(ArrayList<NetworkPlayer> players, int id) {
		//this.game = game;
		
		//game = new BombeStart().start();
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
	
	public void MovePlayerOrSetBomb(int playerID, int keycode){
		ArrayList<Accounts> gamers = game.getMap().getGamers();
		Accounts gamer = gamers.get(playerID);
		gamer.newKey(keycode);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<NetworkPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<NetworkPlayer> players) {
		this.players = players;
	}

	public BombeStart getGame() {
		return game;
	}

	public void setGame(BombeStart game) {
		this.game = game;
	}
	
	

}
