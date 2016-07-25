package tictactoe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.*;
import java.net.*;

import javax.swing.*;
public class GameClient extends JApplet {
	private JPanel grid;
	private JLabel status;
	private InputStream in;
	private OutputStream out;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameClient();
	}
	public GameClient() {
		makeGUI();
		setCon();
		
	}
	private void makeGUI() {
		grid = new JPanel();
		grid.setBackground(Color.BLACK);
		grid.setLayout(new GridLayout(3, 3,5,5));
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++) {
				GamePanel gp = new GamePanel();
				gp.setLoc(i, j);
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status.setText(e.toString());
		}
		
	}
}
