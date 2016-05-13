package packets;

public class PacketStartGame extends Packet {
	
	private int numPlayers;
    private int id;
    private int gameID;
    private int x, y;

    public PacketStartGame(byte[] data) {
        super(00);
        String[] dataArray = readData(data).split(",");
        this.numPlayers = Integer.parseInt(dataArray[0]);
        this.id = Integer.parseInt(dataArray[1]);
        this.gameID = Integer.parseInt(dataArray[2]);
        this.x = Integer.parseInt(dataArray[3]);
        this.y = Integer.parseInt(dataArray[4]);
    }

    public PacketStartGame(int numPlayers, int id, int gameID, int x, int y) {
        super(00);
        this.numPlayers = numPlayers;
        this.id = id;
        this.gameID = gameID;
        this.x = x;
        this.y = y;
    }

    @Override
    public byte[] getData() {
        return ("00" + this.numPlayers + "," + this.id + "," + this.gameID).getBytes();
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getGameID() {
        return gameID;
    }

    public int getPlayerID() {
        return id;
    }

}
