/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import brugerautorisation.transport.soap.Bruger;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author AnwarC
 */
public class Accounts {

    private int x, y, score, ID, dx, dy;
    private Image icon;
    private Bruger loggedIn;
    private final int WITDH = 30;
    private final int HEIGHT = 30;
    private ArrayList<Bombs> bomber = new ArrayList<>();
    private int centrumX = x+15;
    private int centrumY = y+15;
    private int laidBombs = 0;
    private boolean alive;

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
                placeMent = "src/Picture/block.png";
                break;
            case 1:
                placeMent = "src/Picture/block.png";
                break;
            case 2:
                placeMent = "src/Picture/block.png";
                break;
            case 3:
                placeMent = "src/Picture/block.png";
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

    public Accounts(int x, int y, int ID) {
        this.x = x;
        this.y = y;
        this.ID = ID;
        setIcon();
        bomber.add(new Bombs(ID));
        this.alive = true;
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
        return this.x+15;
    }

    public int getCentrumY() {
        return this.y+15;
    }
    public void newBomb(){
        this.bomber.add(new Bombs(this.ID));
    }
    public void newFire(){
        for(int i = 0; i<this.bomber.size(); i++){
            this.bomber.get(i).setBombsize();
        }
    }

    public ArrayList<Bombs> getBomber() {
        return bomber;
    }
    public int getavaBomb(){
        return bomber.size();
    }

    public void setLaidBombs() {
        this.laidBombs += 1;
    }

    public int getLaidBombs() {
        int boml = 0;
        for(int i = 0; i<bomber.size(); i++){
            if(bomber.get(i).getbombstatus() == true){
                boml++;
            }
        }
        
        
        return boml;
    }
    public boolean playerAlive(){
        return this.alive;
    }
    
    public void killPlayer(){
        this.alive = false;
    }

}
