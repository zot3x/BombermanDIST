package packets;

public class PacketBomb extends Packet {
	
	private int playerID;
	private int x, y;
	
	
	public PacketBomb(byte[] data) {
		super(8);
		 String[] dataArray = readData(data).split(",");
	        this.playerID = Integer.parseInt(dataArray[0]);
	        this.x = Integer.parseInt(dataArray[1]);
	        this.y = Integer.parseInt(dataArray[2]);
	}
	
	public PacketBomb(int id, int x, int y) {
        super(8);
        this.playerID = id;
        this.x = x;
        this.y = y;
        }

	@Override
	public byte[] getData() {
		return ("8" + this.playerID + "," + this.x + "," + this.y).getBytes();
	}

}
