package packets;

import java.util.ArrayList;

import Client.ClientPacketListener;
import Client.GameServer;

public class PacketGameState extends Packet {
	
	ArrayList<Integer> bombX = new ArrayList<Integer>();
	ArrayList<Integer> bombY = new ArrayList<Integer>();
	ArrayList<Boolean> bombLaidDown = new ArrayList<Boolean>();
	
	

	public PacketGameState(byte[] data) {
		super(9);
		
	}

	@Override
	public void writeData(ClientPacketListener client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeData(GameServer server) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Integer> getBombX() {
		return bombX;
	}

	public void setBombX(ArrayList<Integer> bombX) {
		this.bombX = bombX;
	}

	public ArrayList<Integer> getBombY() {
		return bombY;
	}

	public void setBombY(ArrayList<Integer> bombY) {
		this.bombY = bombY;
	}

	public ArrayList<Boolean> getBombLaidDown() {
		return bombLaidDown;
	}

	public void setBombLaidDown(ArrayList<Boolean> bombLaidDown) {
		this.bombLaidDown = bombLaidDown;
	}

}
