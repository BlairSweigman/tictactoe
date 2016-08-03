package tictactoe;
import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameServer extends JFrame {
 private GameBoard gb;
 int clientID;
 
 private ArrayList<DataOutputStream> outList;
 private ArrayList<ComHelper> outComs;
 JTextArea status;
	public static void main(String args[]) {
		new GameServer();
	}
	@SuppressWarnings("resource")
	public GameServer() {
		outList = new ArrayList<DataOutputStream>() ;
		outComs = new ArrayList<ComHelper>();
		status = new JTextArea();
		status.setEditable(false);
		add( new JScrollPane(status), BorderLayout.CENTER);
		setTitle("Tic Tac Toe Server");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		gb = new GameBoard();
		try {
			ServerSocket ss = new ServerSocket(7777);
			status.append("Server started on port " + ss.getLocalPort() + "\n");
			clientID = 1;
			while (clientID<=2) {
				Socket s = ss.accept();
				
				status.append("Player " + clientID + " from " + s.getInetAddress().getHostName() + " has signed on\n");
				//get thread 
				ClientTask ct = new ClientTask(s,clientID);
				Thread thread = new Thread(ct);
				thread.start();
				
				clientID++;
			}
		} 
		catch (BindException e) {
			JOptionPane.showMessageDialog(this, "Server already started. Exiting", "Server Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public class ClientTask implements Runnable {
		private Socket socket;
		private int playerID;
		private Player p;
		private ComHelper com;
		char letter;
		public ClientTask(Socket s, int pid) {
			socket = s;
			playerID = pid;
			
		}
		public void run() {
			
			try {
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				
				if (playerID == 1) letter='X';
				else letter='O';
				p = new Player(playerID, letter);
				
				outList.add(playerID-1,out);
				
				
				ComHelper com = new ComHelper(out,p);
				outComs.add(playerID-1,com);
				while (clientID<3) {
					Thread.yield();
				}
				status.append("Let's Play: " + playerID +" \n");
				//out.writeByte((ComHelper.START<<4)+playerID);
				com.sendStart();
				out.flush();
				System.out.println("Player running:"+playerID);
				
				
				while (true) {
					Thread.yield();
					int inMsg = in.readInt() ;	
					Messenger m = new Messenger(inMsg);
					int row = m.getRow();
					int col = m.getCol();
					status.append("Move is :" + row + ","+col+" from Player" + playerID + "\n");
					if (gb.markBoard(row, col, playerID)) {
						if (playerID == 1) {
							broadCastMove("X",row,col);
						}
						else {
							broadCastMove("O",row,col);
						}
					}
					int winPlr  = gb.isWinner();
					if (winPlr>0) {
						status.append(playerID + "  has won!");
						broadCastWin(winPlr);
					}
				}
			}
			catch (SocketException e) {
				status.append(playerID + " has disconnected");
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}
		}
		
	}
	public void broadCastMove(String letter, int row, int col) throws IOException {
		
		for (ComHelper com : outComs) {
			if (letter=="X")
					com.sendX(row, col);
			else
				com.sendO(row, col);
		}
	}
	public void broadCastWin(int pid) throws IOException{
		for (ComHelper com : outComs) {
			com.sendWin(pid);
		}
	}
		
}
