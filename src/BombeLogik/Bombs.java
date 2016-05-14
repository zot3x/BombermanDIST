/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import FireBase.Main;

/**
 ********** REAL HERE**********************************
 *
 * Denne klasse har en masse get og set, som de andre klasser, men den har ogsÃ¥
 * noget logik med hvor ilden skal vÃ¦re.
 *
 */
public class Bombs {

	private int x, y;
	private final int Height = 40, Width = 40;
	private Image img;
	private ArrayList<fireBomb> firebombs = new ArrayList<>();
	private int timeBomb = 0;
	private byte laidDown = 0;
	private final int bombCowndown = 150;
	private int bombOwner;
	private int bombSize = 1;
	private int boxenBoom = 0;
	private int playerKilled = 0;
	private ArrayList<powerUp> bonuses = new ArrayList<>();

	public Bombs(int ownerID) {
		URL bomb = Main.class.getResource("/ImagesBombe/bomb.png");
		ImageIcon WI = new ImageIcon(bomb);
		img = WI.getImage();
		this.bombOwner = ownerID;
		this.firebombs.add(new fireBomb());
		this.firebombs.add(new fireBomb());
		this.firebombs.add(new fireBomb());
		this.firebombs.add(new fireBomb());
		this.firebombs.add(new fireBomb());
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getIcon() {
		return img;
	}

	public int getHeight() {
		return this.Height;
	}

	public int getWidth() {
		return this.Width;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setTimeBomb() {
		this.timeBomb += 1;
	}

	public int getTimeBomb() {
		return this.timeBomb;
	}

	public Image getImg() {
		return img;
	}

	public void resettimebomb() {
		this.timeBomb = 0;
	}

	public void bombBoom(Point[] placesAva, randomBoxes[] randplace) {
		if (this.getTimeBomb() == this.bombCowndown) {
			this.bombExplode();
			this.resettimebomb();
			this.BOOM(placesAva, randplace);
		}
	}

	public void setLaid() {
		for (int i = 0; i < this.firebombs.size(); i++) {
			firebombs.get(i).setFireTime(0);
		}
		this.laidDown = 1;
	}

	public void bombExplode() {
		this.laidDown = 0;

	}

	public byte getbombstatus() {
		return this.laidDown;
	}

	public int getBombOwner() {
		return this.bombOwner;
	}

	public void BOOM(Point[] placesAva, randomBoxes[] randplace) {
		int fireX = this.x;
		int fireY = this.y;
		int fireSize = this.bombSize;

		firebombs.get(0).setFireX(fireX);
		firebombs.get(0).setFireY(fireY);
		firebombs.get(0).setfireOn();

		int counter = 1;
		for (int i = 0; i < fireSize; i++) {
			for (int i1 = 0; i1 < fireSize; i1++) {
				firebombs.get(counter).setFireX(fireX + 40 + (i1 * 40));
				firebombs.get(counter).setFireY(fireY);
				for (int m = 0; m < placesAva.length; m++) {
					if (firebombs.get(counter).getFireX() == placesAva[m].x
							&& firebombs.get(counter).getFireY() == placesAva[m].y) {
						firebombs.get(counter).setfireOn();
						break;
					}
				}
				if (fireSize > 1) {
					for (int r = 0; r < randplace.length; r++) {
						if (randplace[r].getBounds1().intersects(
								firebombs.get(counter).getBounds1())) {
							if (randplace[r].getSprunger() == 0) {
								randplace[r].BOOM();

								for (int l = 0; l < fireSize - i1 - 1; l++) {
									counter++;
								}
							}
							i1 = 1000;
							i = 1000;
							break;
						}
					}
				}

				counter++;
			}
			for (int i2 = 0; i2 < fireSize; i2++) {
				firebombs.get(counter).setFireX(fireX - 40 - (i2 * 40));
				firebombs.get(counter).setFireY(fireY);
				for (int m = 0; m < placesAva.length; m++) {
					if (firebombs.get(counter).getFireX() == placesAva[m].x
							&& firebombs.get(counter).getFireY() == placesAva[m].y) {
						firebombs.get(counter).setfireOn();
						break;
					}
				}

				if (fireSize > 1) {
					for (int r = 0; r < randplace.length; r++) {
						if (randplace[r].getBounds1().intersects(
								firebombs.get(counter).getBounds1())) {
							if (randplace[r].getSprunger() == 0) {
								randplace[r].BOOM();

								for (int l = 0; l < fireSize - i2 - 1; l++) {
									counter++;
								}
							}
							i2 = 1000;
							break;

						}
					}
				}

				counter++;
			}
			for (int i3 = 0; i3 < fireSize; i3++) {
				firebombs.get(counter).setFireX(fireX);
				firebombs.get(counter).setFireY(fireY - 40 - (i3 * 40));
				for (int m = 0; m < placesAva.length; m++) {
					if (firebombs.get(counter).getFireX() == placesAva[m].x
							&& firebombs.get(counter).getFireY() == placesAva[m].y) {
						firebombs.get(counter).setfireOn();
						break;
					}
				}

				if (fireSize > 1) {
					for (int r = 0; r < randplace.length; r++) {
						if (randplace[r].getBounds1().intersects(
								firebombs.get(counter).getBounds1())) {
							if (randplace[r].getSprunger() == 0) {
								randplace[r].BOOM();

								for (int l = 0; l < fireSize - i3 - 1; l++) {
									counter++;
								}
							}
							i3 = 1000;
							break;
						}
					}
				}

				counter++;
			}
			for (int i4 = 0; i4 < fireSize; i4++) {
				firebombs.get(counter).setFireX(fireX);
				firebombs.get(counter).setFireY(fireY + 40 + (i4 * 40));
				for (int m = 0; m < placesAva.length; m++) {
					if (firebombs.get(counter).getFireX() == placesAva[m].x
							&& firebombs.get(counter).getFireY() == placesAva[m].y) {
						firebombs.get(counter).setfireOn();
						break;
					}
				}

				if (fireSize > 1) {
					for (int r = 0; r < randplace.length; r++) {
						if (randplace[r].getBounds1().intersects(
								firebombs.get(counter).getBounds1())) {
							if (randplace[r].getSprunger() == 0) {
								randplace[r].BOOM();
								for (int l = 0; l < fireSize - i4 - 1; l++) {
									counter++;
								}
							}
							i4 = 1000;
							break;
						}
					}
				}

				counter++;
			}
		}

	}

	public void setBombsize() {
		this.firebombs.add(new fireBomb());
		this.firebombs.add(new fireBomb());
		this.firebombs.add(new fireBomb());
		this.firebombs.add(new fireBomb());
		this.bombSize++;
	}

	public ArrayList<fireBomb> getFirebombs() {
		return firebombs;
	}

	public void drawFire(Graphics2D g2d, randomBoxes[] rand,
			ArrayList<Accounts> acc) {
		for (int i = 0; i < firebombs.size(); i++) {
			if (firebombs.get(i).getFireMode() == 1) {
				for (int k = 0; k < rand.length; k++) {
					if (firebombs.get(i).getBounds1()
							.intersects(rand[k].getBounds1())
							&& rand[k].getSprunger() == 0) {
						int j = rand[k].BOOM();
						if (j == 1) {
							System.err.println("hej");
							bonuses.add(new powerUp(rand[k].getX(), rand[k]
									.getY(), j));
						}
						this.boxenBoom++;
					}
					for (int m = 0; m < acc.size(); m++) {
						if (firebombs.get(i).getBounds1()
								.intersects(acc.get(m).getBounds1())
								&& acc.get(m).getAliveInfo() == 1) {
							acc.get(m).killPlayer();
							if (acc.get(m).getID() != this.bombOwner) {
								playerKilled++;
							}
						}
					}

				}
				g2d.drawImage(firebombs.get(i).getFireImg(), firebombs.get(i)
						.getFireX(), firebombs.get(i).getFireY(), null);
				firebombs.get(i).setFireTime();
				firebombs.get(i).fireOff();

			}
		}
	}

	public int getBoxenBoom() {
		return boxenBoom;
	}

	public int getPlayerKilled() {
		return playerKilled;
	}

	public ArrayList<powerUp> getBonuses() {
		return bonuses;
	}

	public String getSendableData(){
		String toSend = String.valueOf((short)x) + String.valueOf((short)y) + String.valueOf(laidDown);
		for(int i = 0; i < firebombs.size(); i++){
			toSend = toSend + String.valueOf(firebombs.get(i).getFireX());
			toSend = toSend + String.valueOf(firebombs.get(i).getFireY());
			toSend = toSend + String.valueOf(firebombs.get(i).getFireMode());
		}
		return toSend;
	}
	public void setLaidBool(byte k){
		this.laidDown = k;
	}
}
