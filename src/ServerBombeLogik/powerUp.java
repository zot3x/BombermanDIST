/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBombeLogik;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author AnwarC
 */
public class powerUp {

	private int x, y, w = 40, h = 40;
	private Image img;
	private boolean avaiable = true;
	private int powerupId;

	public powerUp(int x, int y, int powerUpID) {
		this.x = x;
		this.y = y;
		this.powerupId = powerUpID;

		if (this.powerupId == 1) {
			ImageIcon WI = new ImageIcon("Src/ImagesBombe/bombPowerUp.png");
			img = WI.getImage();
		} else if (this.powerupId == 2) {
			// i = //todo
		}

	}

	public void pickedUp() {
		avaiable = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean avaInfo() {
		return this.avaiable;
	}

	public int getPowerupId() {
		return powerupId;
	}

	public Image getImg() {
		return img;
	}

	public Rectangle getBounds1() {
		return new Rectangle(getX(), getY(), this.w, this.h);
	}
	
	public String getSendableData(){
		String toSend = String.valueOf(x) + String.valueOf(y) + String.valueOf(avaiable) + String.valueOf(powerupId);
		
		return toSend;
	}

}
