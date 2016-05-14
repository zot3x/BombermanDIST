/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import Client.ClientPacketListener;
import Client.ClientPacketSender;
import BombeLogik.drawMap;
//import brugerautorisation.transport.soap.Bruger;
//import brugerautorisation.transport.soap.Brugeradmin;
//import brugerautorisation.transport.soap.BrugeradminImplService;

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

import packets.Packet;
import packets.PacketClientConnect;

/**
 *
 * @author AnwarC
 */
public class BombeStart extends Applet implements Runnable, KeyListener {

	/*
	 * Variabler vi bruger i projectet, jeg gøre projectet ren senere, så de
	 * ligger i deres egne klassr.
	 */
	static final int HEIGHT = 800;
	static final int WIDTH = 650;
	static final String Title = "Bombeman";
	private Image i;
	private Graphics tempI;
	guiComp guicomp = new guiComp();
	private drawMap map;
//	Bruger logIn;
	boolean check = false;
	private final Set<Integer> pressed = new HashSet<>();
	private static Applet instance;
	private static int gameID;
	private DatagramSocket socket;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		if (state == State.PLAYING) {
			pressed.add(e.getKeyCode());
			ArrayList<Integer> keysPressed = new ArrayList<>(pressed);
			for (int i = 0; i < keysPressed.size(); i++) {
				map.newKeyInput(keysPressed.get(i));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed.remove(e.getKeyCode());
	}

	/*
	 * game states.
	 */
	public enum State {
		MENU, PLAYING, DEATH, LOGIN, HIGHSCORE, LOGGEDIN
	}

	State state;

	@Override
	public void init() {
		BombeStart instance = this;
		setSize(HEIGHT, WIDTH);
		setFocusable(true);
		addKeyListener(this);
		state = state.MENU;
		add(guicomp.getLogBut());
		add(guicomp.getHighS());
		add(guicomp.getLogText());
		add(guicomp.getPass());
		add(guicomp.getCheckLog());
		add(guicomp.getWrongC());
		add(guicomp.getSTART2());
		add(guicomp.getSTART3());
		add(guicomp.getSTART4());
		add(guicomp.getHighScore());
		
		map = new drawMap(4);
		state = state.PLAYING;
		
	/*	guicomp.getCheckLog().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				check = true;
				BrugeradminImplService service = new BrugeradminImplService();
				Brugeradmin port = service.getBrugeradminImplPort();

				logIn = port.hentBruger(guicomp.getBrugerText(),
						guicomp.getBrugerPassText());

				if (logIn.getEfternavn() != null) {
					state = state.LOGGEDIN;
				}

			}

		});*/
			
		new ClientPacketListener(this).run();
	}

	/*
	 * starter det tråden.
	 */
	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	/*
	 * run metoden, har loggiken inde i, igen det skal fjernes og laves i dens
	 * egen klasse.
	 */
	@Override
	public void run() {

		guicomp.getHighS().setVisible(true);
		guicomp.getLogBut().setVisible(true);
		guicomp.getLogBut().addActionListener((ActionEvent e) -> {
		//	state = state.LOGIN;
			state = state.PLAYING;
			guicomp.getHighS().setVisible(false);
			guicomp.getLogBut().setVisible(false);
		});
		
		
		Packet packet = new PacketClientConnect(30);
        new ClientPacketSender(socket, packet.getData()).run();

		guicomp.getHighS().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				state = state.HIGHSCORE;
				guicomp.getHighS().setVisible(false);
				guicomp.getLogBut().setVisible(false);
			}
		});
		
		while (true) {
			
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException ex) {
				Logger.getLogger(BombeStart.class.getName()).log(Level.SEVERE,
						null, ex);
			}

		}

	}

	@Override
	public void stop() {
	}

	/*
	 * Får billederne til at se flydende ud. der skal ikke rettes noget her,
	 * den skal bare være der :)
	 */
	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			tempI = i.getGraphics();
		}

		tempI.setColor(getBackground());
		tempI.fillRect(0, 0, this.getSize().width, this.getSize().height);

		tempI.setColor(getForeground());
		paint(tempI);

		g.drawImage(i, 0, 0, this);
	}

	@Override
	public void destroy() {
	}

	/*
	 * paint metoden, loggiken inde her, er bare hvor boksene skal være, o.s.v.
	 * Det kan vel godt flyttes til dens egen klasse.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		ImageIcon im = new ImageIcon("Src/ImagesBombe/background.png");
		Image BI = im.getImage();
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, BI.getWidth(this), 50);
		g2d.setColor(Color.white);
		String tittle = "BOMBEMAN AF ANWAR,NICKLAS,FAYAD OG HADI - DIST PROJECT";
		g2d.drawString(tittle, 200, 30);

		switch (state) {

		case MENU:

			g2d.drawImage(BI, 0, 50, this);

			break;
		case LOGIN:
			if (check == true) {
				guicomp.getWrongC().setVisible(true);
			}

			g2d.drawImage(BI, 0, 50, this);

			guicomp.getLogText().setVisible(true);
			guicomp.getPass().setVisible(true);
			guicomp.getCheckLog().setVisible(true);

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
			guicomp.getSTART2().setVisible(false);
			guicomp.getSTART3().setVisible(false);
			guicomp.getSTART4().setVisible(false);
			guicomp.getHighScore().setVisible(false);

			map.drawplayingMap(g2d);
			break;
		case DEATH:

			break;
		case HIGHSCORE:
			guicomp.getSTART2().setVisible(false);
			guicomp.getSTART3().setVisible(false);
			guicomp.getSTART4().setVisible(false);
			guicomp.getHighScore().setVisible(false);
			break;
		case LOGGEDIN:
			check = false;
			guicomp.getLogText().setVisible(false);
			guicomp.getPass().setVisible(false);
			guicomp.getCheckLog().setVisible(false);
			g2d.setColor(Color.black);
			guicomp.getWrongC().setVisible(false);

			g2d.drawImage(BI, 0, 50, this);
			guicomp.getSTART2().setVisible(true);
			guicomp.getSTART3().setVisible(true);
			guicomp.getSTART4().setVisible(true);
			guicomp.getHighScore().setVisible(true);

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
	
	public Applet getInstance(){
		return instance;
	}
	
	public void setGameID(int gameID){
		this.gameID = gameID;
	}
	
	public static int getGameID(){
		return gameID;
	}
	
	public drawMap getMap(){
		return map;
	}
	
	public void setSocket(DatagramSocket socket){
		this.socket = socket;
	}
}
