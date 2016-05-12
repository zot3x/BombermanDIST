/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JPanel;

/**
 *
 * @author AnwarC
 */
public class guiComp extends JPanel {

	private Button logBut = new Button("Login");
	private Button HighS = new Button("Se highscore");
	private TextField logText = new TextField();
	private TextField pass = new TextField();
	private Button checkLog = new Button("Login");
	private Label wrongC = new Label("WRONG PASSWORD OR USER, TRY AGAIN");
	private Button START2 = new Button("2 spillere");
	private Button START3 = new Button("3 spillere");
	private Button HighScore = new Button("se HighScore");

	private Button START4 = new Button("4 spillere");

	public guiComp() {
		getWrongC().setVisible(false);
		getLogBut().setVisible(false);
		getHighS().setVisible(false);
		getLogText().setVisible(false);
		getPass().setVisible(false);
		getCheckLog().setVisible(false);
		getSTART2().setVisible(false);
		getSTART3().setVisible(false);
		getSTART4().setVisible(false);
		getHighScore().setVisible(false);
		getHighScore().setBounds(350, 600, 100, 40);
		getSTART2().setBounds(350, 420, 100, 40);
		getSTART3().setBounds(350, 480, 100, 40);
		getSTART4().setBounds(350, 540, 100, 40);
		getLogText().setBounds(300, 550, 80, 30);
		getPass().setBounds(400, 550, 80, 30);
		getCheckLog().setBounds(360, 600, 60, 30);
		getLogBut().setBackground(Color.red);
		getHighS().setBackground(Color.red);
		getLogBut().setBounds(300, 600, 100, 30);
		getHighS().setBounds(420, 600, 100, 30);
		getWrongC().setBounds(370, 300, 100, 40);

	}

	public Button getLogBut() {
		return logBut;
	}

	public Button getHighS() {
		return HighS;
	}

	public TextField getLogText() {
		return logText;
	}

	public TextField getPass() {
		return pass;
	}

	public Button getCheckLog() {
		return checkLog;
	}

	public Label getWrongC() {
		return wrongC;
	}

	public String getBrugerText() {
		return getLogText().getText();
	}

	public String getBrugerPassText() {
		return getPass().getText();
	}

	public Button getSTART2() {
		return START2;
	}

	public Button getSTART3() {
		return START3;
	}

	public Button getSTART4() {
		return START4;
	}

	public Button getHighScore() {
		return HighScore;
	}

}
