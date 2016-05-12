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
 ********** REAL HERE**********************************
 *
 * Denne klasse har en masse get og set, du kan vel hurtigt selv finde ud af
 * hvilken der er hvad :) navnene siger sig selv.
 */
public class walls {
	private int x, y, h, w;
	private Image img;

	public walls(int x, int y, int h, int w) {
		ImageIcon WI = new ImageIcon("Src/ImagesBombe/brownstone.png");
		img = WI.getImage();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	public Rectangle getBounds1() {
		return new Rectangle(getX(), getY(), w, h);
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

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}
	
	/*public String getSendableData(){
		String toSend = String.valueOf(x) + String.valueOf(y) + String.valueOf(h) + String.valueOf(w);
		
		return toSend;
	}*/

}
