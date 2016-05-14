package packets;

public class PacketMove extends Packet {

    private int playerID;
    private int gameID;
    private int direction;

    public PacketMove(byte[] data) {
        super(3);
        String[] dataArray = readData(data).split(",");
        this.playerID = Integer.parseInt(dataArray[0]);
        this.gameID = Integer.parseInt(dataArray[1]);
        this.direction = Integer.parseInt(dataArray[2]);
    }

    public PacketMove(int id, int gameID,  int direction) {
        super(3);
        this.playerID = id;
        this.gameID = gameID;
        this.direction = direction;
        
        }

    @Override
    public byte[] getData() {
        return ("3" + this.playerID + "," + this.gameID + "," + this.direction).getBytes();

    }

    public int getID() {
        return playerID;
    }
    

    public int getGameID() {
        return gameID;
    }

    public int getDirection() {
        return this.direction;
    }
}
