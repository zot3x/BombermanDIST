package packets;

public class PacketClientConnect extends Packet {

    private int id;

    public PacketClientConnect(byte[] data) {
        super(1);
        String[] dataArray = readData(data).split(",");
        this.id = Integer.parseInt(dataArray[0]);
    }

    public PacketClientConnect(int id, int x, int y) {
        super(00);
        this.id = id;
    }

    @Override
    public byte[] getData() {
        return ("1" + this.id).getBytes();
    }

    public int getPacketID() {
        return id;
    }

}
