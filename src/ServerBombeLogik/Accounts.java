/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBombeLogik;

import brugerautorisation.transport.soap.Bruger;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 ********** REAL HERE**********************************
 *
 * Denne klasse har en masse get og set, du kan vel hurtigt selv finde ud af
 * hvilken der er hvad :) navnene siger sig selv.
 */
public class Accounts {

	private int x, y, score, ID, dx, dy, speed = 2;
	private Image icon;
	private Bruger loggedIn;
	private final int WITDH = 30;
	private final int HEIGHT = 30;
	private ArrayList<Bombs> bomber = new ArrayList<>();
	private int centrumX = x + 15;
	private int centrumY = y + 15;
	private int laidBombs = 0;
	private byte alive = 0;
	private KeyPress inputs;
	private int playerkilled = 0;
	private int boxesboom = 0;
	private int powerUp = 0;

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void setIcon() {
		String placeMent = "null";
		switch (ID) {
		case 0:
			placeMent = "Src/ImagesBombe/hadi.png";
			break;
		case 1:
			placeMent = "Src/ImagesBombe/rsz_niclas.png";
			break;
		case 2:
			placeMent = "Src/ImagesBombe/rsz_fayad.png";
			break;
		case 3:
			placeMent = "Src/ImagesBombe/rsz_anwar.png";
			break;
		default:
			break;
		}
		ImageIcon ic = new ImageIcon(placeMent);
		this.icon = ic.getImage();
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setLoggedIn(Bruger loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Image getIcon() {
		return icon;
	}

	public Bruger getLoggedIn() {
		return loggedIn;
	}

	public int getScore() {
		return score;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public Accounts(int x, int y, int ID, initMap map) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		setIcon();
		bomber.add(new Bombs(ID));
		this.alive = 1;
		this.inputs = new KeyPress(this, map);
	}

	public Accounts(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public Rectangle getBounds1() {
		return new Rectangle(getX(), getY(), WITDH, HEIGHT);
	}

	public int getCentrumX() {
		return this.x + 15;
	}

	public int getCentrumY() {
		return this.y + 15;
	}

	public void newBomb() {
		this.bomber.add(new Bombs(this.ID));
	}

	public void newFire() {
		for (int i = 0; i < this.bomber.size(); i++) {
			this.bomber.get(i).setBombsize();
		}
	}

	public ArrayList<Bombs> getBomber() {
		return bomber;
	}

	public int getavaBomb() {
		return bomber.size();
	}

	public void setLaidBombs() {
		this.laidBombs += 1;
	}

	public int getLaidBombs() {
		int boml = 0;
		for (int i = 0; i < bomber.size(); i++) {
			if (bomber.get(i).getbombstatus() == 1) {
				boml++;
			}
		}

		return boml;
	}

	public Byte playerAlive() {
		return this.alive;
	}

	public void killPlayer() {
		this.alive = 0;
	}

	public int getID() {
		return ID;
	}

	public int getSpeed() {
		return speed;
	}

	public void newKey(int keyCode) {
		inputs.keyPressed(keyCode);

	}

	public void newLife() {
		this.alive = 1;
	}

	public void setPowerUp(int powerUp) {
		this.powerUp = powerUp;
	}

	public int getBoxesboom() {
		return boxesboom;
	}

	public int getPlayerkilled() {
		return playerkilled;
	}

	public int getPowerUp() {
		return powerUp;
	}

	public void gameOverScores() {
		System.err.println(bomber.get(0).getPlayerKilled()
				+ " <--PLAYERS BOXEN ->>>" + bomber.get(0).getBoxenBoom()
				+ " SIZE ->>>" + bomber.size());
		for (int i = 0; i < bomber.size(); i++) {
			System.err.println(bomber.get(i).getPlayerKilled()
					+ " <--PLAYERS BOXEN ->>>" + bomber.get(i).getBoxenBoom()
					+ " SIZE ->>>" + bomber.size() + " ID ->>>"
					+ bomber.get(i).getBombOwner());

			this.playerkilled += bomber.get(i).getPlayerKilled();
			this.boxesboom += bomber.get(i).getBoxenBoom();
		}

		// TODO this.powerUp = something.
	}

	public Byte getAliveInfo() {
		return this.alive;
	}
	
	public String getSendableData(){
		String toSend = String.valueOf((short)x) + String.valueOf((short)y) + String.valueOf((byte)ID + String.valueOf(alive)) ;
		
		toSend = toSend + bomber.get(0).getSendableData();
		return toSend;
	}
	

}
