package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import BombeLogik.BombeStart;
import packets.*;
import packets.Packet.Packets;

public class ClientPacketHandler{

	private DatagramSocket socket;
	private DatagramPacket packet;
	private boolean response = false;
	byte[] data = null;
	private Packet packetToHandle;
	private BombeStart game;
	
	private InetAddress address;
	private int port;
	
	public ClientPacketHandler(DatagramSocket socket, DatagramPacket packet, BombeStart game){
		this.socket = socket;
		this.packet = packet;
		this.data = packet.getData();
		this.game = game;
		this.address = socket.getInetAddress();
		this.port = socket.getPort();
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				handlePacket();
				if(response){
					DatagramPacket response = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
					try {
						socket.send(response);
					} catch (IOException e) {
						System.out.println("Error sending client response packet to : " + packet.getAddress() + "on port " + packet.getPort());
						e.printStackTrace();
					}
				}
			}
			});
        thread.start();
	}
	/*
	public void run() {
		handlePacket();
		if(response){
			DatagramPacket response = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
			try {
				socket.send(response);
			} catch (IOException e) {
				System.out.println("Error sending client response packet to : " + packet.getAddress() + "on port " + packet.getPort());
				e.printStackTrace();
			}
		}
	}
	*/
	private void handlePacket(){
		String message = new String(data);
		Packets type = Packet.checkPacketID(message.substring(0, 1));
		switch (type) {
		default:
		case INVALID:
			break;
		case CONNECT:
			System.out.println("INSIDE CONNECT CLIENT");
			PacketClientConnectResponse packetClientConnectResponse = new PacketClientConnectResponse(data);
			handleClientConnectResponse(packetClientConnectResponse);
			break;
		case GAMESTATE:
			PacketGameState packetGameState = new PacketGameState(data);
			handleGameState(packetGameState);
		case READY:
		}
	}
	
	private void handleClientConnectResponse(
			PacketClientConnectResponse packetClientConnectResponse) {
		System.out.println("PACKET CLIENTRESPONSE ID's " + packetClientConnectResponse.getGameID() + " " + packetClientConnectResponse.getId());
		game.setGameID(packetClientConnectResponse.getGameID());
		game.setPlayerID(packetClientConnectResponse.getId());
	}
	
	public void handleGameState(PacketGameState gameState){
    	game.getMap();
    	int counter = 0;
    	for(int i = 0; i<4;i++){
        	System.out.println("NULLPOINTER GAMEX = " + gameState.getGamersX()[i]);
    		game.getMap().getGamers().get(i).setX(gameState.getGamersX()[i]);
    		game.getMap().getGamers().get(i).setY(gameState.getGamersY()[i]);
    		game.getMap().getGamers().get(i).setPlayerAliveInfo(gameState.getGamersAlive()[i]);
    		game.getMap().getGamers().get(i).getBomber().get(0).setX(gameState.getBombX()[i]);
    		game.getMap().getGamers().get(i).getBomber().get(0).setY(gameState.getBombY()[i]);
    		game.getMap().getGamers().get(i).getBomber().get(0).setLaidBool(gameState.getBombLaid()[i]);
    		for(int j = 0;j<5;j++){
    			game.getMap().getGamers().get(i).getBomber().get(0).getFirebombs().get(j).setFireX(gameState.getFireX()[counter]);
    			game.getMap().getGamers().get(i).getBomber().get(0).getFirebombs().get(j).setFireY(gameState.getFireY()[counter]);
    			game.getMap().getGamers().get(i).getBomber().get(0).getFirebombs().get(j).setFireMode(gameState.getFireMode()[counter]);
    			counter++;

    		}
    		
    	}
    	for(int i = 0; i<105;i++){
    		game.getMap().getRandBox()[i].setMode(gameState.getRandSprunget()[i]);
    	}
    }
}