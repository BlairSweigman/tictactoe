package tictactoe;
import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.util.ArrayList;

public class GameServer extends JFrame {
 private GameBoard gb;
 int clientID;
 private ArrayList<DataInputStream> inList;
 private ArrayList<DataOutputStream> outList;
 JTextArea status;
	public static void main(String args[]) {
		new GameServer();
	}
	public GameServer() {
		outList = new ArrayList<DataOutputStream>() ;
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public class ClientTask implements Runnable {
		private Socket socket;
		private int playerID;
		public ClientTask(Socket s, int pid) {
			socket = s;
			playerID = pid;
		}
		public void run() {
			
			try {
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				outList.add(playerID-1,out);
				//out.writeByte(ComHelper.PLAYER+playerID);
				while (clientID<3) {
					Thread.yield();
				}
				status.append("Let's Play: " + playerID +" \n");
				out.writeByte((ComHelper.START<<4)+playerID);
				out.flush();
				System.out.println("Player running:"+playerID);
				
				
				while (true) {
					Thread.yield();
					int inMsg = in.readByte() ;	
					int row = inMsg >> 2;
					int col = inMsg & 3;
					status.append("Move is :" + row + ","+col+"\n");
					if (gb.markBoard(row, col, playerID)) {
						if (playerID == 1) {
							broadCast(ComHelper.X,inMsg);
						}
						else {
							broadCast(ComHelper.O,inMsg);
						}
					}
				}
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}
		}
		
	}
	public void broadCast(int msg, int data) throws IOException {
		for (DataOutputStream out : outList) {
			out.writeByte((msg <<4)+data);
		}
	}
		
}
