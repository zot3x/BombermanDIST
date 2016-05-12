package packets;

import Client.ClientPacketListener;
import Client.GameServer;

public class PacketMove extends Packet {

    private int playerID;
    private int x, y;

    public PacketMove(byte[] data) {
        super(2);
        String[] dataArray = readData(data).split(",");
        this.playerID = Integer.parseInt(dataArray[0]);
        this.x = Integer.parseInt(dataArray[1]);
        this.y = Integer.parseInt(dataArray[2]);
    }

    public PacketMove(int id, int x, int y) {
        super(2);
        this.playerID = id;
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
        return ("2" + this.playerID + "," + this.x + "," + this.y).getBytes();

    }

    public int getID() {
        return id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
