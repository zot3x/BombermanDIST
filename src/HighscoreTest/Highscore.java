package HighscoreTest;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.synth.SynthSpinnerUI;
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
	Object[] columnNames = {"Navn", "Score"};
	Object[][] data = new Object[10][10];
	ArrayList<String> nameList = new ArrayList<>();
	ArrayList<Integer> pointList = new ArrayList<Integer>();

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

		final JTable table = new JTable(data, columnNames);

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
	}

	public void updateTable(){

		try {
			firebaseController.getHighscore().getTop10Score(new FirebaseListener(){

				@Override
				public void top10(ArrayList<FirebaseUser> scoreList) {

					super.top10(scoreList);

					for(int i = 0 ; i < 10 && i < scoreList.size() ; i++){
						nameList.add(scoreList.get(i).Navn);
						pointList.add(scoreList.get(i).Highscore);
					}

					for(int s = 0 ; s < 10 && s < nameList.size() ; s++){
						for(int j = 0; j < 1; j++){
							data[s][j] = nameList.get(s);
							data[s][j+1] = pointList.get(s);
						}
					}
				}});
		} catch (FirebaseDataException e) {
			e.printStackTrace();
		}
	}
}