package HighscoreTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mainpanel extends JFrame {

	public JPanel contentPane;
	Gameplay objgameplay = new Gameplay();
	HighscorePanel objhighscore;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainpanel frame = new Mainpanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainpanel() {
		objhighscore = new HighscorePanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setResizable(false);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setContentPane(objgameplay);
			}
		});
		btnNewButton.setFont(new Font("Forte", Font.PLAIN, 32));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(292, 300, 215, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Highscore");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setContentPane(objhighscore);
			}
		});
		btnNewButton_1.setFont(new Font("Forte", Font.PLAIN, 32));
		btnNewButton_1.setBounds(292, 360, 215, 51);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("src/Picture/background.png"));
		lblNewLabel.setBounds(0, 0, 760, 600);
		contentPane.add(lblNewLabel);
		contentPane.setPreferredSize(new Dimension(760, 600));

		//setBounds(0, 0, 800, 645);
		pack();
	}
	public Mainpanel(int x)
	{
		
	}
	
	
}
