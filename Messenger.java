package tictactoe;

/**
 * Messenger - prepares bytecode message to send to and from server.
 * @author Blair Sweigman
 *
 */

public class Messenger {
	 public static final int START = 0x10;
	 public static final int X = 0x20;
	 public static final int O = 0x40;
	 public static final int INVALID =0x80;
	 public static final int WIN =0x100;
	 public static final int DRAW = 0x200;
	 public static final int ROWMASK = 0xC;
	 public static final int COLMASK = 0x3;
	 public static final int DATAMASK = 0xF;
	 public static final int CODEMASK = 0xfff0;
	 public static final int ROWSHIFT = 0x2;
	 private int message;
	 
	 /**
	  * Create a new Messenger
	 * @param mess - message to decode
	 */
	 public Messenger(int mess) {
		 message = mess;
	 }
	 
	 /**
	  * Gets the bytecode message to send
	 * @return - the message
	 */
	public int getMessage() {
		 return message;
	}
	
	/**
	 * returns Data portion of the message
	 * @return data portion
	 */
	public int getData() {
		 return message & DATAMASK;
	}
	
	/**
	 * @return codes for message
	 */
	public int getCode() {
		return  message & CODEMASK;
	}
	
	/**
	 * @return row of move
	 */
	public  int getRow() {
		return (getData() & ROWMASK) >>> ROWSHIFT;
	}
	/**
	 * @return column of move
	 */
	public  int getCol() {
		return (getData() & COLMASK);
	}
	
	/**
	 * @return String - letter that was sent
	 */
	public String getLetter() {
		if ((getCode() & X)==X) {
			return  "X";
		}
		else if ((getCode() & O)==O) {
			return "O";
		}
		else return "";
	}
	
	/**
	 * @return boolean Start of game
	 */
	public boolean isStartMsg() {
		if((message & START)==START) {
			return true;
		}
		else return false;
	}
	/**
	 * @return true if start of game, else if not
	 */
	public boolean isWinMsg() {
		if((message & WIN)==WIN) {
			return true;
		}
		else return false;
	}
	/**
	 * Check to see if a draw(tie) message has been sent
	 * @return true if it is a draw, false if not
	 */
	public boolean isDraw() {
		if ((message & DRAW)==DRAW) 
			return true;
		else 
			return false;
	
	}
	/**
	 * Checks to see if invalid move made
	 * @return true if it is an invalid move
	 */
	public boolean isInvalid() {
		if ((message & INVALID)== INVALID) {
			return true;
		}
		else return false;
				
	}
	/**
	 * Test function
	 * @param args
	 */
	public static void main(String[] args) {
		Messenger m = new Messenger(0x129);
		System.out.println(Integer.toBinaryString(0x129));
		System.out.println(Integer.toBinaryString(m.getCode()));
		System.out.println(m.getRow() + "," + m.getCol());
		System.out.println(m.getLetter());
		if (m.isWinMsg()) {
			System.out.println("Win!");
		}
	}
}
