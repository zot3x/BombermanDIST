package packets;

public class PacketConnect extends Packet {

	private int numPlayers;
    private int id;
    private int x, y;

    public PacketConnect(byte[] data) {
        super(00);
        String[] dataArray = readData(data).split(",");
        this.numPlayers = Integer.parseInt(dataArray[0]);
        this.id = Integer.parseInt(dataArray[1]);
        this.x = Integer.parseInt(dataArray[2]);
        this.y = Integer.parseInt(dataArray[3]);
    }

    public PacketConnect(int numPlayers, int id, int x, int y) {
        super(00);
        this.numPlayers = numPlayers;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public byte[] getData() {
        return ("00" + this.id + "," + getX() + "," + getY()).getBytes();
    }

    public int getUsername() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
