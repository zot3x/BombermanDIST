/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBombeLogik;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author AnwarC
 */
public class drawMap {

	private initMap map;
	private ArrayList<Bombs> laidbombs;
	private Point[] bombposA;
	public ArrayList<Accounts> gamers;
	private int playerKilledBonus = 50;
	private int boxBoomBonus = 5;
	private ArrayList<powerUp> powerUps = new ArrayList<>();

	public drawMap(int Players) {
		map = new initMap(Players);
		this.laidbombs = map.laidbombs;
		this.bombposA = map.getBombposA();
		gamers = map.getGamers();

	}

	public void drawLogInMap(Graphics2D g2d) {
		// todo
	}

	public void drawhighscoreMap(Graphics2D g2d) {
		// todo
	}

	public void drawplayingMap() {

		for (int i = 0; i < gamers.size(); i++) {
			pickedUPBonus(gamers.get(i));
			for (int j = 0; j < gamers.get(i).getBomber().size(); j++) {
				gamers.get(i).getBomber().get(j)
						.drawFire(map.getRandBox(), gamers);
			}
		}

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
		for (int i = 0; i < gamers.size(); i++) {
			gamers.get(i).newKey(keycode);
		}
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
		for(int i = 0; i<map.getRandBox().length; i++){
		toSend = toSend + map.getRandBox()[i].getSendableData();
		}
		return toSend;
	}
	
}
