/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author AnwarC
 */
public class fireBomb {

    private int fireX;
    private int fireY;
    private int fireW = 40;
    private int fireH = 40;
    private Image fireImg;
    private int fireTime = 0;
    private boolean fireon = false;
    private int fireTimeOn = 50;

    public fireBomb() {
        ImageIcon WI = new ImageIcon("C:/Users/AnwarC/Desktop/resized/rsz_explosion.png");
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

    public void setFireTime() {
        System.err.println(this.fireTime);
        this.fireTime +=1;
    }

    public int getFireTime() {
        return this.fireTime;
    }

    public Image getFireImg() {
        return fireImg;
    }
    public void setfireOn(){
        this.fireon = true;
    }
    public void setfireOf(){
        this.fireon = false;
    }
    public boolean getFireMode(){
        return fireon;
    }
    public void fireOff(){
        if(this.fireTime >= this.fireTimeOn){
            this.setfireOf();
            this.fireTime = 0;
        }
    }
    
        public Rectangle getBounds1() {
        return new Rectangle(this.fireX, this.fireY, this.fireH, this.fireW);
    }
    
    

}
