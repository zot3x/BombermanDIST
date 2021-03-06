/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import FireBase.Main;

/**
 ********** REAL HERE**********************************
 *
 * Denne klasse har en masse get og set, du kan vel hurtigt selv finde ud af
 * hvilken der er hvad :) navnene siger sig selv.
 */
public class fireBomb {

	private int fireX;
	private int fireY;
	private int fireW = 40;
	private int fireH = 40;
	private Image fireImg;
	private int fireTime = 0;
	private byte fireon = 0;
	private int fireTimeOn = 35;

	public fireBomb() {
		ImageIcon WI = new ImageIcon(Main.class.getResource("/ImagesBombe/rsz_explosion.png"));
		fireImg = WI.getImage();
	}

	public void setFireH(int fireH) {
		this.fireH = fireH;
	}

	public void setFireW(int fireW) {
		this.fireW = fireW;
	}

	public void setFireX(int fireX) {
		this.fireX = fireX;
	}

	public void setFireY(int fireY) {
		this.fireY = fireY;
	}

	public int getFireH() {
		return fireH;
	}

	public int getFireW() {
		return this.fireW;
	}

	public int getFireX() {
		return this.fireX;
	}

	public int getFireY() {
		return this.fireY;
	}
	
	public void setFireMode(byte k){
		this.fireon = k;
	}

	public void setFireTime() {
		this.fireTime += 1;
	}

	public int getFireTime() {
		return this.fireTime;
	}

	public Image getFireImg() {
		return fireImg;
	}

	public void setfireOn() {
		this.fireon = 1;
	}

	public void setfireOf() {
		this.fireon = 0;
		this.fireTime = 0;
	}

	public byte getFireMode() {
		return fireon;
	}

	public void fireOff() {
		if (this.fireTime >= this.fireTimeOn) {
			this.setfireOf();

		}
	}

	public Rectangle getBounds1() {
		return new Rectangle(this.fireX, this.fireY, this.fireH, this.fireW);
	}

	public void setFireTime(int fireTime) {
		this.fireTime = fireTime;
	}

	public int getFireTimeOn() {
		return fireTimeOn;
	}

	public String getSendableData(){
		String toSend = String.valueOf(fireX) + String.valueOf(fireY) + String.valueOf(fireon);
		return toSend;
	}
}
