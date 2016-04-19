package Server;

import java.io.*;

import Server.ConnectionListener;

public class Server {

	public static void main(String[] args) throws IOException {
		System.out.println("Server Started");
		
		new ConnectionListener().run();
	}

}
