package packets;

import Client.ClientPacketListener;
import Client.GameServer;

public class PacketConnect extends Packet {

    private int id;
    private int x, y;

    public PacketConnect(byte[] data) {
        super(00);
        String[] dataArray = readData(data).split(",");
        this.id = Integer.parseInt(dataArray[0]);
        this.x = Integer.parseInt(dataArray[1]);
        this.y = Integer.parseInt(dataArray[2]);
    }

    public PacketConnect(int id, int x, int y) {
        super(00);
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public void writeData(ClientPacketListener client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
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
