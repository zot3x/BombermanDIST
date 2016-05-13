package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import packets.Packet;
import packets.PacketClientConnect;
import packets.PacketConnect;
import packets.PacketDisconnect;
import packets.PacketGameState;
import packets.PacketMove;
import packets.Packet.Packets;
import Server.NetworkPlayer;
import BombeLogik.BombeStart;

public class ClientPacketListener extends Thread {

    private InetAddress ipAddress;
    private static DatagramSocket socket;
    private BombeStart game;
    private int port = 12346;

    public ClientPacketListener(BombeStart game) {
        this.game = game;
        try {
            ClientPacketListener.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getLocalHost();
            
            Packet packet = new PacketClientConnect(30);
            new ClientPacketSender(socket, packet.getData()).run();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void run() {
    	
    	
    	
        while (true) {
            byte[] data = new byte[512];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new ClientPacketHandler(socket, packet, game).run();
        }
    }
    
    public static DatagramSocket getSocket(){
    	return socket;
    }

 /*   private void handlePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        Packets type = Packet.checkPacketID(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
        default:
        case INVALID:
            break;
        case CONNECT:
            packet = new PacketConnect(data);
            handleConnect((PacketConnect) packet, address, port);
            break;
        case DISCONNECT:
            packet = new PacketDisconnect(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + ((PacketDisconnect) packet).getUsername() + " has disconnected...");
            //remove player
            break;
        case MOVE:
            packet = new PacketMove(data);
          //  handleMove((PacketMove) packet);
        }
    }
*/
  /*  public void sendData(byte[] data) {
    	DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
    	try {
    		socket.send(packet);
    	} catch (IOException e) {
    		e.printStackTrace();
        }
    }

    public void handleConnect(PacketConnect packet, InetAddress address, int port) {
        System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUsername()
                + " has joined the game...");
        //add player to game
        
    }
    */
/*    public static void handleGameState(PacketGameState gameState){
    	
    }
*/
   /* private void handleMove(PacketMove packet) {
        movePlayer(packet.getX(), packet.getY(), packet.getNumSteps(),
                packet.isMoving(), packet.getMovingDir());
    }*/
}
