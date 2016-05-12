/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBombeLogik;

import Server.ServerPacketListener;
import Server.ServerPacketSender;
import brugerautorisation.transport.soap.Bruger;
import brugerautorisation.transport.soap.Brugeradmin;
import brugerautorisation.transport.soap.BrugeradminImplService;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

/**
 *
 * @author AnwarC
 */
public class BombeStart implements Runnable {

	drawMap map;
	Bruger logIn;
	int numPlayers;
	DatagramSocket socket;

	public enum State {
		MENU, PLAYING, DEATH, LOGIN, HIGHSCORE, LOGGEDIN
	}

	State state;
	
	public BombeStart(int numPlayers, DatagramSocket socket){
		this.numPlayers = numPlayers;
		this.socket = socket;
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				map = new drawMap(numPlayers);
				
				//do some stuff here
				
				
				while(true){
					tick();
					
					try {
						Thread.sleep(33,33); //gotta be approx 30 times a sec
					} catch (InterruptedException ex) {
						Logger.getLogger(BombeStart.class.getName()).log(Level.SEVERE,
								null, ex);
					}
				}
				
			}
		});
		thread.start();
	}

	/*
	 * starter det tråden.
	 */
/*	public void start(int players) {
		Thread thread = new Thread(this);
		thread.start();
	}
*/

	@Override
	public void run() {
		int counter = 0;
		while (true) {
			if(counter == 5){ // how often do we want to send out gamestate??
				tick();
				counter = 0;
			}
			counter++;
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException ex) {
				Logger.getLogger(BombeStart.class.getName()).log(Level.SEVERE,
						null, ex);
			}

		}

	}
	
	private void tick(){
		String Gamestate = getSendableData();
		new ServerPacketSender(socket, )
	}





	public void paint() {

		switch (state) {

		case MENU:



			break;
		case LOGIN:

			break;
		case PLAYING:
			int alive = 0;
			for (int i = 0; i < map.gamers.size(); i++) {
				if (map.gamers.get(i).playerAlive() == 1) {
					alive++;
				}
			}
			if (alive < 2) {
				map.gameOver();
				state = State.LOGGEDIN;
			}
			break;
		case DEATH:

			break;
		case HIGHSCORE:
			break;
		case LOGGEDIN:
			break;

		default:
			break;
		}

	}
	
	public String getSendableData(){
		String toSend;
		
		toSend = map.getSendableData();
		return toSend;
	}
	
	
	public drawMap getMap(){
		return map;
	}

}
