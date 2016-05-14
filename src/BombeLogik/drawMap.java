/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import FireBase.Main;

/**
 *
 * @author AnwarC
 */
public class drawMap {

	private initMap map;
	private ArrayList<Bombs> laidbombs;
	private Point[] bombposA;
	ArrayList<Accounts> gamers;
	private int playerKilledBonus = 50;
	private int boxBoomBonus = 5;
	private ArrayList<powerUp> powerUps = new ArrayList<>();
	private randomBoxes[] randBox;


	public drawMap(int Players) {
		map = new initMap(Players);
		this.laidbombs = map.laidbombs;
		this.bombposA = map.getBombposA();
		gamers = map.getGamers();
		this.randBox = map.getRandBox();
	}

	public void drawLogInMap(Graphics2D g2d) {
		// todo
	}

	public void drawhighscoreMap(Graphics2D g2d) {
		// todo
	}

	public void drawplayingMap(Graphics2D g2d) {

		ImageIcon PI = new ImageIcon(Main.class.getResource("/ImagesBombe/asphalt.jpg"));
		Image BPI = PI.getImage();
		g2d.drawImage(BPI, 0, 50, null);
		for (int i = 0; i < gamers.size(); i++) {
			if (gamers.get(i).playerAlive() == 1) {
				g2d.drawImage(gamers.get(i).getIcon(), gamers.get(i).getX(),
						gamers.get(i).getY(), null);
			}
		}
		for (int i = 0; i < map.getWalltop().length; i++) {
			g2d.drawImage(map.getWalltop()[i].getImg(),
					map.getWalltop()[i].getX(), map.getWalltop()[i].getY(),
					null);
		}
		for (int i = 0; i < map.getWallleft().length; i++) {
			g2d.drawImage(map.getWallleft()[i].getImg(),
					map.getWallleft()[i].getX(), map.getWallleft()[i].getY(),
					null);
		}
		for (int i = 0; i < map.getWallbot().length; i++) {
			g2d.drawImage(map.getWallbot()[i].getImg(),
					map.getWallbot()[i].getX(), map.getWallbot()[i].getY(),
					null);
		}
		for (int i = 0; i < map.getWallright().length; i++) {
			g2d.drawImage(map.getWallright()[i].getImg(),
					map.getWallright()[i].getX(), map.getWallright()[i].getY(),
					null);
		}
		for (int i = 0; i < map.getWallsmid().length; i++) {
			g2d.drawImage(map.getWallsmid()[i].getImg(),
					map.getWallsmid()[i].getX(), map.getWallsmid()[i].getY(),
					null);
		}
		for (int i = 0; i < map.getRandBox().length; i++) {
			byte boxSprunget = map.getRandBox()[i].getSprunger();
			if (boxSprunget == 0) {
				g2d.drawImage(map.getRandBox()[i].getImg(),
						map.getRandBox()[i].getX(), map.getRandBox()[i].getY(),
						null);
			}
		}
		for (int i = 0; i < gamers.size(); i++) {
			for (int j = 0; j < gamers.get(i).getBomber().size(); j++) {
				for (int k = 0; k < gamers.get(i).getBomber().get(j)
						.getBonuses().size(); k++) {
					if (gamers.get(i).getBomber().get(j).getBonuses().get(k)
							.avaInfo() == true) {
						g2d.drawImage(gamers.get(i).getBomber().get(j)
								.getBonuses().get(k).getImg(), gamers.get(i)
								.getBomber().get(j).getBonuses().get(k).getX(),
								gamers.get(i).getBomber().get(j).getBonuses()
										.get(k).getY(), null);
					}
				}
			}
		}
		for (int i = 0; i < laidbombs.size(); i++) {
			if (laidbombs.get(i).getbombstatus() == 1) {
				int J = laidbombs.get(i).getBombOwner();
				for (int m = 0; m < gamers.get(J).getavaBomb(); m++) {
					if (gamers.get(J).getBomber().get(m).getbombstatus() == 1) {
						g2d.drawImage(
								gamers.get(J).getBomber().get(m).getImg(),
								gamers.get(J).getBomber().get(m).getX(), gamers
										.get(J).getBomber().get(m).getY(), null);
					}
				}
			}
		}

		for (int i = 0; i < gamers.size(); i++) {
			pickedUPBonus(gamers.get(i));
			for (int j = 0; j < gamers.get(i).getBomber().size(); j++) {
				gamers.get(i).getBomber().get(j)
						.drawFire(g2d, map.getRandBox(), gamers);
			}
		}
		
		for(int i = 0; i<gamers.size(); i++){
			if(gamers.get(i).getBomber().get(0).getbombstatus() == 1){
				g2d.drawImage(gamers.get(i).getBomber().get(0).getImg(), gamers.get(i).getBomber().get(0).getX(), gamers.get(i).getBomber().get(0).getY(), null);
			}
		}
		
		
		checkLaidBomb();
		checkFireBombs(laidbombs, map.getBombposA());

	}

	private void checkFireBombs(ArrayList<Bombs> laidbombs, Point[] bombposA) {

		
		for (int i = 0; i < laidbombs.size(); i++) {
			if (laidbombs.get(i).getbombstatus() == 1) {
				laidbombs.get(i).setTimeBomb();
				laidbombs.get(i).bombBoom(bombposA, map.getRandBox());
			}
			if (laidbombs.get(i).getbombstatus() == 0) {
				laidbombs.remove(i);
				i = i - 1;

			}
		}
	}

	public void newKeyInput(int keycode) {
	//	for (int i = 0; i < gamers.size(); i++) {
			gamers.get(0).newKey(keycode);
	//	}
	}

	public ArrayList<Accounts> getGamers() {
		return gamers;
	}

	public void gameOver() {
		for (int i = 0; i < gamers.size(); i++) {
			gamers.get(i).gameOverScores();
			int finalScore = 0;
			finalScore += gamers.get(i).getPlayerkilled() * playerKilledBonus;
			finalScore += gamers.get(i).getBoxesboom() * boxBoomBonus;
			gamers.get(i).setScore(finalScore);
			System.err.println(gamers.get(i).getID()
					+ " <- Spiller id, fik score: " + gamers.get(i).getScore());
		}

	}

	private void pickedUPBonus(Accounts acc) {
		for (int i = 0; i < gamers.size(); i++) {
			for (int j = 0; j < gamers.get(i).getBomber().size(); j++) {
				for (int k = 0; k < gamers.get(i).getBomber().get(j)
						.getBonuses().size(); k++) {
					if (gamers.get(i).getBomber().get(j).getBonuses().get(k)
							.avaInfo() == true
							&& acc.getBounds1().intersects(
									gamers.get(i).getBomber().get(j)
											.getBonuses().get(k).getBounds1())) {
						acc.newBomb();
						gamers.get(i).getBomber().get(j).getBonuses().get(k)
								.pickedUp();
					}
				}
			}
		}

	}

	
	public String getSendableData(){
		String toSend = null;
		toSend = gamers.get(0).getSendableData();
		for(int i = 1; i<4; i++){
			toSend = toSend + gamers.get(i).getSendableData();
		}
		toSend = toSend + ",";
		for(int i = 0; i<map.getRandBox().length; i++){
		toSend = toSend + map.getRandBox()[i].getSendableData();
		}
		return toSend;
	}
	
	public randomBoxes[] getRandBox() {
		return randBox;
	}
	
	
	private void checkLaidBomb(){
		for(int i = 0; i<gamers.size();i++){
			Bombs tempBomb = gamers.get(i).getBomber().get(0);
		
			if(tempBomb.getbombstatus() == 1){
				for(int j = 0; j<laidbombs.size(); j++){
					if(laidbombs.get(j).getBombOwner() != tempBomb.getBombOwner()){
						laidbombs.add(tempBomb);
					}
				}
			}
		
			
		}
	}
}
