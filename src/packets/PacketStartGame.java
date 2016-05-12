package packets;

public class PacketStartGame extends Packet {
	
	private int playerID;
	
	public PacketStartGame(byte[] data) {
		super(6);
		String[] dataArray = readData(data).split(",");
	    this.playerID = Integer.parseInt(dataArray[0]);
	}

	@Override
	public byte[] getData() {
		 return ("2" + this.playerID).getBytes();
	}

}