package tictactoe;
import java.awt.BorderLayout;
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
		grid.setLayout(new GridLayout(3, 3));
		
		add(grid, BorderLayout.CENTER);
		status = new JLabel("Tic Tac Toe Started");
		add(status, BorderLayout.SOUTH);
		setSize(400, 400);
	
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
