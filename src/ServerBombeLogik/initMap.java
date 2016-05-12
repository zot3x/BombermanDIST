/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerBombeLogik;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author AnwarC
 */
public class initMap {

	Point[] bombposA = new Point[130];
	walls[] walltop = new walls[17];
	walls[] wallbot = new walls[17];
	walls[] wallleft = new walls[12];
	walls[] wallright = new walls[12];
	walls[] wallsmid = new walls[35];
	private int[] startX = new int[4];
	private int[] startY = new int[4];
	randomBoxes[] randBox = new randomBoxes[105];
	ArrayList<Accounts> gamers = new ArrayList<>();
	ArrayList<Bombs> laidbombs = new ArrayList<>();

	public initMap(int playerCount) {
		startX[0] = 80;
		startX[1] = 640;
		startX[2] = 80;
		startX[3] = 640;
		startY[0] = 140;
		startY[1] = 140;
		startY[2] = 540;
		startY[3] = 540;

		for (int i = 0; i < 17; i++) {
			walltop[i] = new walls(40 + (i * 40), 100, 40, 40);
			wallbot[i] = new walls(40 + (i * 40), 580, 40, 40);
		}
		for (int i = 0; i < 12; i++) {
			wallleft[i] = new walls(40, 140 + (40 * i), 40, 40);
			wallright[i] = new walls(680, 140 + (40 * i), 40, 40);
		}
		int cW = 0;

		for (int j = 0; j < 5; j++) {
			for (int k = 0; k < 7; k++) {
				wallsmid[cW] = new walls(120 + (k * 80), 180 + (j * 80), 40, 40);
				cW++;
			}

		}
		int cRB = 0;

		for (int i = 0; i < 11; i++) {
			if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
				for (int j = 0; j < 11; j++) {
					randBox[cRB] = new randomBoxes(160 + (j * 40),
							140 + (i * 40));
					cRB++;
				}
			} else {
				for (int j = 0; j < 5; j++) {
					randBox[cRB] = new randomBoxes(160 + (j * 80),
							140 + (i * 40));
					cRB++;
				}
			}
		}
		for (int i = 0; i < 7; i++) {
			randBox[cRB] = new randomBoxes(startX[0], startY[0] + 80 + (i * 40));
			cRB++;
			randBox[cRB] = new randomBoxes(startX[1], startY[1] + 80 + (i * 40));
			cRB++;

		}
		setBombpos();
		for (int i = 0; i < playerCount; i++) {
			gamers.add(new Accounts(startX[i], startY[i], i, this));
		}
	}

	public randomBoxes[] getRandBox() {
		return randBox;
	}

	public int[] getStartX() {
		return startX;
	}

	public int[] getStartY() {
		return startY;
	}

	public walls[] getWallbot() {
		return wallbot;
	}

	public walls[] getWallleft() {
		return wallleft;
	}

	public walls[] getWallright() {
		return wallright;
	}

	public walls[] getWallsmid() {
		return wallsmid;
	}

	public walls[] getWalltop() {
		return walltop;
	}

	private void setBombpos() {
		int pc = 0;
		for (int i = 0; i < 11; i++) {
			if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
				for (int j = 0; j < 15; j++) {
					bombposA[pc] = new Point(startX[0] + (j * 40), startY[0]
							+ (i * 40));
					pc++;
				}
			} else {
				for (int j = 0; j < 8; j++) {
					bombposA[pc] = new Point(startX[0] + (j * 80), startY[0]
							+ (i * 40));
					pc++;
				}
			}

		}
	}

	public Point[] getBombposA() {
		return bombposA;
	}

	public ArrayList<Accounts> getGamers() {
		return gamers;
	}

	public ArrayList<Bombs> getLaidbombs() {
		return laidbombs;
	}





}
