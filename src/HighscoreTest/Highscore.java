package HighscoreTest;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import FireBase.FirebaseController;
import FireBase.FirebaseDataException;
import FireBase.FirebaseListener;
import FireBase.FirebaseUser;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Highscore extends JPanel {
	
	Mainpanel mp = new Mainpanel(10);
	FirebaseController firebaseController;
	FirebaseListener fbl;
	String[] columnNames = {"Navn", "Score"};
//	Object[][] data;
	/**
	 * Create the panel.
	 */
	public Highscore(FirebaseController fireBaseController) {
		this.firebaseController = fireBaseController;
		updateTable();

		setBounds(0, 0, 800, 600);
		setLayout(null);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mp = new Mainpanel();
				//mp.repaint();
				mp.setVisible(true);

			}
		});
		btnNewButton.setIcon(new ImageIcon("src/Picture/150px-Back.png"));
		btnNewButton.setBounds(0, 500, 111, 42);
		add(btnNewButton);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("src/Picture/BackgroundHighscore.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 600);
		add(lblNewLabel);

		final JTable table = new JTable();

		//		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(false);
		table.setEnabled(false);

		table.setRowHeight(30);
		table.setBackground(Color.ORANGE);
		table.getTableHeader().setBackground(Color.BLUE);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 150, 300, 328);
		lblNewLabel.add(scrollPane, BorderLayout.CENTER);

		//		JLabel highscore = new JLabel("HIGHSCORE");
		//		highscore.setBounds(200,200,400,300);
		//		lblNewLabel.add(highscore);
	}

	public void updateTable(){
		
		try {
			firebaseController.Highscore().getTop10Score(new FirebaseListener(){

				@Override
				public void top10(ArrayList<FirebaseUser> scoreList) {
					super.top10(scoreList);
				}
				
			});
		} catch (FirebaseDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
