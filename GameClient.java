package tictactoe;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


import javax.swing.*;
/**
 * GameClient - Client for Tic Tac Toe
 * 
 * @author Blair Sweigman
 *
 */
/**
 * @author blair
 *
 */
/**
 * @author blair
 *
 */
@SuppressWarnings("serial")
public class GameClient extends JApplet {
	private JPanel grid;
	private JLabel status;
	private ArrayList<GamePanel> playBoard;
	private DataInputStream in;
	private DataOutputStream out;
	private int playerID;
	//private String toDraw;
	//private Player p ;
	private static String hostname;
	public static void main(String[] args) {
		
	}
	
	

	
	/* Start up Applet */
	public void init() {
		getHost();
		makeGUI();
		setCon();
		RunGame rg = new RunGame();
		Thread rt = new Thread(rg);
		rt.start();
	}
	
	/**
	 *  getHost - tries to get host from Applet parameters
	 * 	sets host to localhost if hostname absent
	 */
	private void getHost(){
		if (this.getParameter("hostname")!= null)
		   hostname =this.getParameter("hostname");
		else
			hostname = "localhost";
	}
	
	/**
	 *  makeGUI - builds the GUI for the client
	 */
	private void makeGUI() {
		grid = new JPanel();
		grid.setBackground(Color.BLACK);
		
		grid.setLayout(new GridLayout(3, 3,5,5));
		playBoard = new ArrayList<>();
		for (int i=0;i<3;i++)
			for (int j=0;j<3;j++) {
				GamePanel gp = new GamePanel();
				gp.setLoc(i, j);
				gp.setWaiting(true);
				gp.addActionListener(new GPListener() );
				//gp.setPanel("O");
				grid.add(gp);
				playBoard.add(gp);
				//gp.repaint();
			}
		add(grid, BorderLayout.CENTER);
		status = new JLabel("Tic Tac Toe Started");
		add(status, BorderLayout.SOUTH);
		setSize(300, 300);
		setVisible(true);
	}
	
	/**
	 * setWaiting - disables player from making a move when it is not their turn
	 * @param b - true disables panels, false enables the panels
	 */
	private void setWaiting(boolean b) {
		
		for (GamePanel gamePanel : playBoard) {
			gamePanel.setWaiting(b);
		}
		
	}
	/**
	 * setCon - Connects to server
	 */
	@SuppressWarnings("resource")
	private void setCon() {
		try {
			Socket s = new Socket(hostname,7777);
			status.setText("Connected to server: " + hostname);
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
		} catch(ConnectException e){
			JOptionPane.showMessageDialog(this,"Cannot connect to server: " + hostname, "Connection Error",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
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
					int inMsg = in.readInt();
					System.out.println("From server:"+ Integer.toHexString(inMsg));
					//ComHelper com = new ComHelper(inMsg);
					Messenger m = new Messenger(inMsg);
					
					if (m.isStartMsg()) {
						playerID=m.getData();
						//p = new Player(playerID);
						if (playerID==1) {
							
							setWaiting(false);
							status.setText("Game started. You are X and you go First");
						}
						else {
							setWaiting(true);
							status.setText("Game started. You are O. Player X starts");
						}
					}
					
					else if (m.getLetter()=="X")	{
						{playLetter(m);}
					}
					else if (m.getLetter()=="O") 
					{playLetter(m);}
					else if (m.isWinMsg())  {
						int winPlyr = m.getData();
						if (winPlyr == playerID)
						    status.setText("You  won!");
						else
							status.setText("You Lost! Good Day Sir!");
						for (GamePanel gp : playBoard) {
							gp.setWaiting(true);
						}
					}
					else if (m.isDraw()) {
						status.setText("It is a Draw!");
					}
					else if (m.isInvalid()) {
						status.setText("Sorry, that is not a valid move");
					}
					
				}
			}
			catch (IOException e) {
				status.setText(e.toString());
			}
		}
		/**
		 * Puts letter on the board, and switches turns
		 * @param m the message from the board
		 * @throws IOException
		 */
		public void playLetter(Messenger m) throws IOException{
			int x = m.getRow();
			int y = m.getCol();
			for (GamePanel gp : playBoard) {
				gp.setWaiting(!gp.isWaiting());
				if( ( gp.getRow() ==x) && (gp.getCol() ==y)) {
					gp.setPanel(m.getLetter());
					
					
				}
				gp.repaint();
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
				out.writeInt(msg);
				}
				catch(IOException e) {
					System.err.println(e.toString() );
				}
			}
			//send coordinates to server
			
		}
	}


	
}
