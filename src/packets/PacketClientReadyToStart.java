package packets;

public class PacketClientReadyToStart extends Packet {
	
	private int playerID;
	
	public PacketClientReadyToStart(byte[] data) {
		super(7);
		String[] dataArray = readData(data).split(",");
	    this.playerID = Integer.parseInt(dataArray[0]);
		
	}

	@Override
	public byte[] getData() {
		 return ("2" + this.playerID).getBytes();
	}

}
