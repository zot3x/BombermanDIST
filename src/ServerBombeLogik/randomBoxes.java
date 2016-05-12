/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBombeLogik;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 ********** REAL HERE**********************************
 *
 * Denne klasse har en masse get og set, du kan vel hurtigt selv finde ud af
 * hvilken der er hvad :) navnene siger sig selv.
 */
public class randomBoxes {
	private int x, y;
	private final int height = 40;
	private final int width = 40;
	private byte sprunget = 0;
	private Image img;

	public randomBoxes(int x, int y) {
		ImageIcon WI = new ImageIcon("Src/ImagesBombe/block.png");
		img = WI.getImage();
		this.x = x;
		this.y = y;

	}

	public Image getImg() {
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Rectangle getBounds1() {
		return new Rectangle(getX(), getY(), width, height);
	}

	public byte getSprunger() {
		return this.sprunget;
	}

	public int BOOM() {
		int bonus = 0;

		Random randnum = new Random();

		int nextRan = randnum.nextInt(50);

		if (nextRan < 10) {
			bonus = 1;
		} else if (nextRan < 20 && nextRan > 9) {
			bonus = 2;
		}

		this.sprunget = 1;

		return bonus;
	}

	public String getSendableData(){
		String toSend = String.valueOf((short)x) + String.valueOf((short)y) + String.valueOf(sprunget);
		
		return toSend;
	}
}
