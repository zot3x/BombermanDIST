/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 **********REAL HERE**********************************
 *
denne klasse bruges ikke endnu, men det er her jeg regner med at  vi skal have key inputs fra brugeren.

 */
package BombeLogik;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import packets.PacketMove;
import Client.ClientPacketListener;
import Client.ClientPacketSender;

/**
 *
 * @author AnwarC
 */
public class KeyPress extends KeyAdapter {

	private int input1 = 0;
	private int input2 = 0;
	private int input3 = 0;
	private int input4 = 0;
	private int input5 = 0;
	private Accounts player;
	private initMap map;

	public KeyPress(Accounts play, initMap Map) {
		this.map = Map;

			input1 = KeyEvent.VK_LEFT;
			input2 = KeyEvent.VK_DOWN;
			input3 = KeyEvent.VK_RIGHT;
			input4 = KeyEvent.VK_UP;
			input5 = KeyEvent.VK_SPACE;

		this.player = play;
	}
	
	private String getMovableData(int keycode){
		String toSend = String.valueOf(3) + String.valueOf(player.getID()) + "," + String.valueOf(BombeStart.getGameID()) + "," + String.valueOf(keycode);
		return toSend;
	}

	public void keyPressed(int Keycode) {
		
		//new ClientPacketSender(ClientPacketListener.getSocket(), getMovableData(Keycode).getBytes()).run();
		
		if (player.playerAlive() == 1) {
			Accounts tempA = new Accounts(player.getX(), player.getY());
			boolean goodMove = true;

			if (Keycode == input1) {
				player.setDx(-player.getSpeed());
				tempA.setX(tempA.getX() + player.getDx());
				for (int i = 0; i < map.getWallsmid().length; i++) {
					boolean validMove = validMove(tempA, map.getWallsmid()[i]);

					if (validMove == false) {
						goodMove = false;
						i = map.getWallsmid().length + 1;
					}
				}
				if (goodMove == true) {
					for (int i = 0; i < map.getRandBox().length; i++) {

						boolean validMove = true;
						if (map.getRandBox()[i].getSprunger() == 0) {
							validMove = validMoveRand(tempA,
									map.getRandBox()[i]);
						}
						if (validMove == false) {
							goodMove = false;
							i = map.getRandBox().length + 1;
						}

					}
					if (goodMove == true) {

						if (map.getWallleft()[0].getX() + 40 < tempA.getX()) {
							player.setX(tempA.getX());
							new ClientPacketSender(ClientPacketListener.getSocket(), getMovableData(Keycode).getBytes());

						}
					}
				}

			} else if (Keycode == input2) {
				player.setDy(player.getSpeed());
				tempA.setY(tempA.getY() + player.getDy());
				for (int i = 0; i < map.getWallsmid().length; i++) {
					boolean validMove = validMove(tempA, map.getWallsmid()[i]);

					if (validMove == false) {
						goodMove = false;
						i = map.getWallsmid().length + 1;
					}
				}
				if (goodMove == true) {
					for (int i = 0; i < map.getRandBox().length; i++) {

						boolean validMove = true;
						if (map.getRandBox()[i].getSprunger() == 0) {
							validMove = validMoveRand(tempA,
									map.getRandBox()[i]);
						}
						if (validMove == false) {
							goodMove = false;
							i = map.getRandBox().length + 1;

						}

					}
					if (goodMove == true) {

						if (map.getWallbot()[0].getY() > tempA.getY() + 30) {
							player.setY(tempA.getY());
							new ClientPacketSender(ClientPacketListener.getSocket(), getMovableData(Keycode).getBytes());

						}
					}
				}

			} else if (Keycode == input3) {
				player.setDx(player.getSpeed());
				tempA.setX(tempA.getX() + player.getDx());
				for (int i = 0; i < map.getWallsmid().length; i++) {
					boolean validMove = validMove(tempA, map.getWallsmid()[i]);

					if (validMove == false) {
						i = map.getWallsmid().length + 1;
						goodMove = false;
					}
				}
				if (goodMove == true) {
					for (int i = 0; i < map.getRandBox().length; i++) {

						boolean validMove = true;
						if (map.getRandBox()[i].getSprunger() == 0) {
							validMove = validMoveRand(tempA,
									map.getRandBox()[i]);
						}
						if (validMove == false) {
							goodMove = false;
							i = map.getRandBox().length + 1;
						}

					}
					if (goodMove == true) {

						if (map.getWallright()[0].getX() > tempA.getX() + 30) {
							player.setX(tempA.getX());
							new ClientPacketSender(ClientPacketListener.getSocket(), getMovableData(Keycode).getBytes());

						}
					}
				}

			} else if (Keycode == input4) {

				player.setDy(-player.getSpeed());
				tempA.setY(tempA.getY() + player.getDy());
				for (int i = 0; i < map.getWallsmid().length; i++) {
					boolean validMove = validMove(tempA, map.getWallsmid()[i]);

					if (validMove == false) {
						goodMove = false;
						i = map.getWallsmid().length + 1;
					}
				}
				if (goodMove == true) {
					for (int i = 0; i < map.getRandBox().length; i++) {

						boolean validMove = true;
						if (map.getRandBox()[i].getSprunger() == 0) {
							validMove = validMoveRand(tempA,
									map.getRandBox()[i]);
						}
						if (validMove == false) {
							goodMove = false;
							i = map.getRandBox().length + 1;

						}

					}
					if (goodMove == true) {

						if (map.getWalltop()[0].getY() + 40 < tempA.getY()) {
							player.setY(tempA.getY());
							new ClientPacketSender(ClientPacketListener.getSocket(), getMovableData(Keycode).getBytes());

						}
					}
				}

			} else if (Keycode == input5) {
				boolean bombCheck =	setBomb(player);
				if(bombCheck == true){
					new ClientPacketSender(ClientPacketListener.getSocket(), getMovableData(Keycode).getBytes());

				}
			}

		}
	}

	public int getInput1() {
		return input1;
	}

	public int getInput2() {
		return input2;
	}

	public int getInput3() {
		return input3;
	}

	public int getInput4() {
		return input4;
	}

	private boolean validMove(Accounts acc, walls w) {
		Rectangle r1 = acc.getBounds1();
		Rectangle r2 = w.getBounds1();

		if (r1.intersects(r2)) {
			return false;
		}

		return true;
	}

	private boolean validMoveRand(Accounts acc, randomBoxes ran) {

		Rectangle r1 = acc.getBounds1();
		Rectangle r2 = ran.getBounds1();

		if (r1.intersects(r2)) {
			return false;
		}

		return true;
	}

	private Point findBombPos(Accounts acc) {
		Point bombpos;
		int foundPoint = 0;
		int accx = acc.getCentrumX();
		int accy = acc.getCentrumY();

		for (int i = 0; i < map.getBombposA().length; i++) {
			if (accx > map.getBombposA()[i].x
					&& accx < map.getBombposA()[i].x + 40
					&& accy > map.getBombposA()[i].y
					&& accy < map.getBombposA()[i].y + 40) {
				foundPoint = i;
				i = 20000;
			}
		}

		bombpos = map.getBombposA()[foundPoint];

		return bombpos;
	}

	private boolean setBomb(Accounts acc) {
		boolean bombAvaliable = false;
		if (acc.getLaidBombs() < acc.getavaBomb()) {
			Point bombpos = findBombPos(acc);
			map.getLaidbombs().add(acc.getBomber().get(acc.getLaidBombs()));
			acc.getBomber().get(acc.getLaidBombs()).setX(bombpos.x);
			acc.getBomber().get(acc.getLaidBombs()).setY(bombpos.y);
			acc.getBomber().get(acc.getLaidBombs()).setLaid();
			acc.setLaidBombs();
			bombAvaliable = true;

		}
		return bombAvaliable;
	}

}
