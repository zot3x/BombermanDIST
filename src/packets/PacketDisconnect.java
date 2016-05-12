package packets;

import java.net.InetAddress;

import Client.ClientPacketListener;
import Client.GameServer;


public class PacketDisconnect extends Packet {

    private int id;
    private InetAddress address;
    private int port;

    public PacketDisconnect(byte[] data) {
        super(01);
        this.id = Integer.parseInt(readData(data));
    }

    public PacketDisconnect(int id) {
        super(01);
        this.id = id;
    }

    @Override
    public byte[] getData() {
        return ("01" + id).getBytes();
    }

    public int getUsername() {
        return id;
    }

}
