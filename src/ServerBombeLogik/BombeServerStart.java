/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBombeLogik;

import Server.NetworkPlayer;
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
public class BombeServerStart {

	private drawMap map;
	Bruger logIn;
	int numPlayers;
	DatagramSocket socket;
	ArrayList<NetworkPlayer> players;

	public enum State {
		MENU, PLAYING, DEATH, LOGIN, HIGHSCORE, LOGGEDIN
	}

	State state;

	public BombeServerStart(int numPlayers, DatagramSocket socket,
			ArrayList<NetworkPlayer> players) {
		this.numPlayers = numPlayers;
		this.socket = socket;
		this.players = players;

		Thread thread = new Thread(new Runnable() {
			public void run() {
				map = new drawMap(numPlayers);

				// do some stuff here

				while (true) {
					tick();

					try {
						Thread.sleep(25); // gotta be approx 30 times a sec
					} catch (InterruptedException ex) {
						Logger.getLogger(BombeServerStart.class.getName()).log(
								Level.SEVERE, null, ex);
					}
				}

			}
		});
		thread.start();
	}

	private void tick() {
		// Send out gamestate
		String Gamestate = getSendableData();
		new ServerPacketSender(socket, Gamestate.getBytes(), players);

		// Check if players alive

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

	public String getSendableData() {
		String toSend;

		toSend = map.getSendableData();
		return toSend;
	}

	public drawMap getMap() {
		return map;
	}

}
