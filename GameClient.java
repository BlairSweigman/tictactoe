package tictactoe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;
@SuppressWarnings("serial")
public class GameClient extends JApplet {
	private JPanel grid;
	private JLabel status;
	private DataInputStream in;
	private DataOutputStream out;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameClient();
	}
	public GameClient() {
		makeGUI();
		setCon();
		RunGame rg = new RunGame();
		Thread rt = new Thread(rg);
		rt.start();

	}
	private void makeGUI() {
		grid = new JPanel();
		grid.setBackground(Color.BLACK);
		
		grid.setLayout(new GridLayout(3, 3,5,5));
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++) {
				GamePanel gp = new GamePanel();
				gp.setLoc(i, j);
				gp.setWaiting(false);
				gp.addActionListener(new GPListener() );
				//gp.setPanel("O");
				grid.add(gp);
				//gp.repaint();
			}
		add(grid, BorderLayout.CENTER);
		status = new JLabel("Tic Tac Toe Started");
		add(status, BorderLayout.SOUTH);
		setSize(800, 800);
		
		setVisible(true);
	}
	private void setCon() {
		try {
			Socket s = new Socket("localhost",7777);
			status.setText("Connected to server");
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status.setText(e.toString());
		}
		   
			//for (Component p : grid.getComponents()) {
				
			
		
		
	}
	
	class RunGame implements Runnable {
		public void run() {
			try { 
				while (true) {
					int inMsg = in.readByte();
					System.out.println("From server:"+ inMsg);
				}
			}
			catch (IOException e) {
				status.setText(e.toString());
			}
		}
	}
	class GPListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			GamePanel ac = (GamePanel) arg0.getSource();
			System.out.println("Grid Clicked:" + ac.getRow() + "," + ac.getCol());
			if (ac.isWaiting()) {
				System.out.println("Waiting for other Player");
			}
		
			
			else {
				int msg;
				msg = (ac.getRow() <<2) + ac.getCol();
				System.out.println(Integer.toBinaryString(msg));
				try {
				out.writeByte(msg);
				}
				catch(IOException e) {
					System.err.println(e.toString() );
				}
			}
			//send coordinates to server
			
		}
	}


	
}
