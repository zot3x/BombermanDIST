package HighscoreTest;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Gameplay extends JPanel {
	
	ArrayList<Point> points= new ArrayList<Point>();
	 Random r= new Random();
	 int temp=0;
	 
	public Gameplay() {
		setSize(760, 600);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("asphalt.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 600);
		add(lblNewLabel);
		
		for(int i = 0; i<20; i++){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("brownstone.PNG"));
			lblNewLabel_1.setBounds(i*40, 0, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}
		
		for(int i = 0; i<20; i++){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("brownstone.PNG"));
			lblNewLabel_1.setBounds(i*40, 560, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}
			
		for(int i = 0; i<=13; i++){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("brownstone.PNG"));
			lblNewLabel_1.setBounds(0, i*40, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}
		
		for(int i = 0; i<=13; i++){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("brownstone.PNG"));
			lblNewLabel_1.setBounds(720, i*40, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}
		
		for (int i = 80; i<13*40; i=80+i){
			for (int j = 80; j<18*40; j=80+j){
				JLabel lblNewLabel_1 = new JLabel("New label");
				lblNewLabel_1.setIcon(new ImageIcon("brownstone.PNG"));
				lblNewLabel_1.setBounds(j, i, 40, 40);
				lblNewLabel.add(lblNewLabel_1);
				}
		}
		
		for (int i = 120; i<13*40; i=80+i){
			for (int j = 40; j<18*40; j=80+j){
				
				points.add(new Point(j, i));
				/*JLabel lblNewLabel_1 = new JLabel("New label");
				lblNewLabel_1.setIcon(new ImageIcon(".png"));
				lblNewLabel_1.setBounds(j,i, 40, 40);
				lblNewLabel.add(lblNewLabel_1);*/
				
				
				}
		}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 40));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 520));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		/*for(int i = 3; i<=15; i++){
			if(i%2!=0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 520, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
		
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 240));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 480));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 320));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		/*for(int i = 3; i<=15; i++){
			if(i%2!=0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 240, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
	/*
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 480, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 320, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
		for(int i = 2; i<=20; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 360));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		/*for(int i = 2; i<20; i++){
			if(i%2==0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 360, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
		for(int i = 2; i<=20; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 440));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		/*
		for(int i = 2; i<20; i++){
			if(i%2==0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 440, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
		
		for(int i = 2; i<=20; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 280));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		/*
		for(int i = 2; i<20; i++){
			if(i%2==0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 280, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
		/*
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 400, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 400));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 160));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		/*for(int i = 3; i<=15; i++){
			if(i%2!=0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 160, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}*/
		for(int i = 2; i<=20; i++){
			if(i%2==0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 200, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}
		for(int i = 2; i<=20; i++){
			if(i%2==0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 120, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}
		for(int i = 3; i<=15; i++){
			if(i%2!=0){
				
			points.add(new Point(i*40, 80));	
			
		}}
		for(int i=0;i<points.size();i++){
			
			temp=r.nextInt(points.size())+1;
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(points.get(temp-1).x, points.get(temp-1).y, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
			
			
		}
		points.clear();
		/*for(int i = 3; i<=15; i++){
			if(i%2!=0){
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("block.png"));
			lblNewLabel_1.setBounds(i*40, 80, 40, 40);
			lblNewLabel.add(lblNewLabel_1);
		}}
		*/
	}
}
