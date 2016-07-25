package tictactoe;

/**
 * Helps out communication between server and client:
 * server sends the following information:
 * bit 0-1 colomn id, player id
 * bit 2-3 row id
 * bit 4 - player registered
 * bit 5 - player X move
 * bit 6 - winning player
 * @author blair
 * 
 */
public class ComHelper {
 public static final int PLAYER =16;
 public static final int X = 32;
 public static final int WINNER = 32;
 public static final int ROWSHIFT = 2;
 public static final int COLMASK = 3;
 private int message;
 public ComHelper (int message) {
	 this.message = message;
 }
 public int getMessage() {
	 return message;
 }
 public boolean isPlayerMsg() {
	 if((message & PLAYER)==PLAYER) {
		 return true;
	 }
	 else return false;
 }
 public boolean isWinMsg() {
	 if((message & WINNER)==WINNER) {
		 return true;
	 }
	 else return false;
 }
 public static void main(String[] args) {
	 ComHelper c = new ComHelper(10);
	 System.out.println(c.getRow());
	 System.out.println(c.getCol());
 }
 public  int getRow() {
	 return (message >> ROWSHIFT);
 }
 public  int getCol() {
	 return (message & COLMASK);
 }
}
