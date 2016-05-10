package HighscoreTest;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import FireBase.FirebaseController;
import FireBase.FirebaseDataException;
import FireBase.FirebaseListener;
import FireBase.FirebaseUser;
import FireBase.HighScoreController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Highscore extends JPanel {

	private boolean DEBUG = false;
	
	FirebaseUser fbU = new FirebaseUser();
	ArrayList<FirebaseUser> scoreList = new ArrayList<FirebaseUser>();
	private String[] columnNames = {"Navn", "Score"};
	private Object[][] data = {{"s144885", 500}, {"s144885", 400}, {"s144885", 300}, {"s144885", 200}, {"s144885", 5000}, {"s144885", 800} ,
			{"s144885", 676}, {"s144885", 554}, {"s144885", 550}, {"s144885", 900}};
	
	
	Mainpanel mp = new Mainpanel(10);
	/**
	 * Create the panel.
	 */

	public Highscore() {

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

		String[] columnNames = {"Navn", "Score"};

	
		data[0][1] = 234;
		data[0][0] = "james";
		System.out.println(data[0][0]);
		
		
		for(int i = 0; i<10;i++){
			data[i][0] = "brugernan";
			
			for(int j = 1; j<2; j++){
				data[i][j] = 10;

			}
		}
		
		
//		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(false);
		table.setEnabled(false);
		
		if (DEBUG) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					printDebugData(table);
				}

				private void printDebugData(JTable table) {
					int numRows = table.getRowCount();
					int numCols = table.getColumnCount();
					javax.swing.table.TableModel model = table.getModel();

					System.out.println("Value of data: ");
					for (int i=0; i < numRows; i++) {
						System.out.print("    row " + i + ":");
						for (int j=0; j < numCols; j++) {
							System.out.print("  " + model.getValueAt(i, j));
						}
						System.out.println();
					}
					System.out.println("--------------------------");						
				}
			});
		}
		

	}
	public JTable getJTable(){
		final JTable table = new JTable(data, columnNames);
		
		table.setRowHeight(30);
		table.setBackground(Color.ORANGE);
		table.getTableHeader().setBackground(Color.BLUE);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(250, 150, 300, 328);
		lblNewLabel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		
		return table;
	}
}
