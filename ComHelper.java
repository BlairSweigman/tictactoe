package tictactoe;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Helps out communication between server and client:
 * 
 * @author Blair Sweigman
 * 
 */


public class ComHelper {






	private int message;
	private DataOutputStream out;
	private Player player;

	/**
	 * Constructor
	 * @param out the DataOuputStream for outbound communication
	 * @param p Player Object that represents the player
	 */
	public ComHelper ( DataOutputStream out,Player p) {
		this.out = out;
		player = p;

	}

	/**
	 * Sends Start Message to Client
	 * @throws IOException
	 */
	public void sendStart() throws IOException {
		int m = Messenger.START + player.getPlayerID();
		out.writeInt(m);
	}

	/**
	 * Marks Board with X
	 * @param row the row to mark
	 * @param col the column to mark
	 * @throws IOException
	 */
	public void sendX(int row, int col) throws IOException {
		out.writeInt( Messenger.X + (row<<Messenger.ROWSHIFT) + col);

	}
	/**
	 * Marks Board with O
	 * @param row the row to mark
	 * @param col the column to mark
	 * @throws IOException
	 */
	public void sendO(int row, int col) throws IOException {
		out.writeInt( Messenger.O + (row<<Messenger.ROWSHIFT) + col);
	}
	/**
	 * Sends invalid message
	 * @throws IOException
	 */
	public void sendInvalid() throws IOException{
		out.writeInt(Messenger.INVALID);
	}
	/**
	 * Sends Winning message to clients
	 * @param winningPlayer the winning player
	 * @throws IOException
	 */
	public void sendWin(int winningPlayer) throws IOException { 
		out.writeInt( Messenger.WIN + winningPlayer);
	}
	/**
	 * Sends a message to the client that there is a draw
	 * @throws IOException
	 */
	public void sendDraw() throws IOException {
		out.writeInt(Messenger.DRAW);
	}
	/** 
	 * gets the message
	 * @return the message
	 */
	public int getMessage() {
		return message;
	}


	public static void main(String[] args) {

	}



}
