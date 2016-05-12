package packets;

import Client.ClientPacketListener;
import Client.GameServer;

public class PacketStartGame extends Packet {
	
	private int playerID;
	
	public PacketStartGame(byte[] data) {
		super(6);
		String[] dataArray = readData(data).split(",");
	    this.playerID = Integer.parseInt(dataArray[0]);
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
		 return ("2" + this.playerID).getBytes();
	}

}