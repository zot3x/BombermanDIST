package Client;

import java.io.*;
import java.net.*;

public class ClientConnection {
	
	private static Socket clientSocket;
	private ObjectInputStream objectInput;
	private ObjectOutputStream objectOutput;
	private boolean connected;
	private Thread serverListenerThread;
	
	
	public ClientConnection(){
	}
	
	
	public void connect(InetAddress hostAddress) {

        try {
            clientSocket = new Socket(hostAddress, 1234);
          //  clientSocket.setTcpNoDelay(true);
             
             objectInput =  new ObjectInputStream(clientSocket.getInputStream());
             objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
             System.out.println("Connection to server Established");
             connected = true;
             startServerResponseThread();

        } catch (IOException e) {
            System.out.println("Failure in connecting to server");
            e.printStackTrace();
            connected = false;
        }
    }

   private void startServerResponseThread() {
	   
      Runnable runnable = new Runnable() {

         public void run () {
        	 System.out.println("Server Response Thread started");
            try {
               while (true) {
                  retrieveGameState(objectInput.readObject());
               }
            } catch (SocketException e) {
            	System.out.println("Network failure");
            	e.printStackTrace();
            } catch (IOException e) {
              e.printStackTrace();
            } catch (Exception e) {
            	e.printStackTrace();
            } finally {
               connected = false;
               if (serverListenerThread != null) {
            	   System.out.println("Closing serverListenerThread");
            	   return;
               }
            }
         }
      };
  
      serverListenerThread = new Thread(runnable);
      serverListenerThread.start();

   }
	
   public void retrieveGameState(Object object){
	   
   }
   
   public void sendMove(){
	   
   }
}
