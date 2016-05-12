package Server;

import java.io.*;

import Server.ServerPacketListener;

public class Server {

	public static void main(String[] args) throws IOException {
		System.out.println("Server Started");
		
		new ServerPacketListener().run();
	}

}
