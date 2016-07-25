package tictactoe;

/**
 * Helps out communication between server and client:
 * server sends the following information:
 * bit 0-3 panel coordinates, player id
 * bit 4-7 Message
 * message pattern:
 * 		0001 (1) register player
 * 		0010 (2) winner with player id
 * 		1111 (15) X sent with coords
 * 		0000 (0) O sent with coords 
 * 		0100 (4) Game start
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
 public ComHelper (int msgBits, int dataBits) {
	 message = (msgBits << 4) + dataBits;
	 
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
	 ComHelper c = new ComHelper(7,10);
	 System.out.println(c.getMsg());
	 System.out.println(c.getData());
	 System.out.println(c.getRow());
	 System.out.println(c.getCol());

 }
 public  int getRow() {
	 return (getData() >> ROWSHIFT);
 }
 public  int getCol() {
	 return (getData() & COLMASK);
 }
 private int getMsg() {
	 return (message >> 4);
 }
 private int getData() {
	 return (message & 15);
 }
}
