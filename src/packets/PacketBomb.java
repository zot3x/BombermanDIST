package packets;

public class PacketBomb extends Packet {
	
	private int playerID;
	private int gameID;
	private int direction;
	
	
	public PacketBomb(byte[] data) {
		super(8);
		 String[] dataArray = readData(data).split(",");
	        this.playerID = Integer.parseInt(dataArray[0]);
	        this.gameID = Integer.parseInt(dataArray[1]);
	        this.direction = Integer.parseInt(dataArray[2]);
	}
	
	public PacketBomb(int id, int x, int y) {
        super(8);
        this.playerID = id;
        this.x = x;
        this.y = y;
        }

	@Override
	public byte[] getData() {
		return ("8" + this.playerID + "," + this.gameID + "," + this.direction).getBytes();
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getDirection() {
		return direction;
	}
}
