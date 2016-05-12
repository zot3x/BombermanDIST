package packets;

public abstract class Packet {

//    private byte packetID;
	
    public static enum Packets {
        INVALID(0), CONNECT(1), DISCONNECT(2), MOVE(3), BOMB(4), READY(5),STARTGAME(6), GAMESTATE(9);

        private int packetID;

        private Packets(int packetID) {
            this.packetID = packetID;
        }

        public int getId() {
            return packetID;
        }
    }

    public Packet(int packetID) {
  //      this.packetID = (byte) packetID;
    }

    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(1);
    }

    public abstract byte[] getData();

    public static Packets checkPacketID(String packetID) {
        try {
            return checkPacketID(Integer.parseInt(packetID));
        } catch (NumberFormatException e) {
            return Packets.INVALID;
        }
    }

    public static Packets checkPacketID(int id) {
        for (Packets packet : Packets.values()) {
            if (packet.getId() == id) {
                return packet;
            }
        }
        return Packets.INVALID;
    }
}
