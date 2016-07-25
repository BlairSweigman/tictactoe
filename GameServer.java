package tictactoe;
import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class GameServer extends JFrame {
 private GameBoard gb;
 JTextArea status;
	public static void main(String args[]) {
		new GameServer();
	}
	public GameServer() {
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
			int clientID = 1;
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
		Socket socket;
		int playerID;
		public ClientTask(Socket s, int pid) {
			socket = s;
			playerID = pid;
		}
		public void run() {
			try {
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeByte(16+playerID);
				while (true) {
					int inMsg = in.readByte() ;	
					int row = inMsg >> 2;
					int col = inMsg & 3;
					status.append("Move is :" + row + ","+col+"\n");
				}
			}
			catch (IOException e) {
				System.err.println(e.toString());
			}
		}
		
	}
}
