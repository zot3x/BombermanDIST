package packets;

public class PacketClientConnectResponse extends Packet {
	
	private int id;
	private int gameID;

	public PacketClientConnectResponse(byte[] data) {
		super(1);
		  String[] dataArray = readData(data).split(",");
	      this.id = Integer.parseInt(dataArray[0]);
	      this.gameID = Integer.parseInt(dataArray[1]);
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

}
