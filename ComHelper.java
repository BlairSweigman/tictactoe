package tictactoe;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Helps out communication between server and client:
 * server sends the following information:
 * bit 0-3 panel coordinates, player id
 * bit 4-7 Message
 * message pattern:
 * 		0001 ---- (1) register player
 * 		0010 ---- (2) winner with player id
 * 		1111 (15) X sent with coords
 * 		0000 (0) O sent with coords 
 * 		0100 (4) Game start
 * 		1000 (8) Invalid Move
 * bit 4 - player registered
 * bit 5 - player X move
 * bit 6 - winning player
 * @author blair 
 * 
 */
public class ComHelper {


 

 
 
 private int message;
 private DataOutputStream out;
 private Player player;
 public ComHelper ( DataOutputStream out,Player p) {
	 this.out = out;
	 player = p;
	
 }
 public void sendStart() throws IOException {
	 int m = Messenger.START + player.getPlayerID();
	 out.writeInt(m);
 }
 public void sendX(int row, int col) throws IOException {
	 out.writeInt( Messenger.X + (row<<Messenger.ROWSHIFT) + col);
	
 }
 public void sendO(int row, int col) throws IOException {
	 out.writeInt( Messenger.O + (row<<Messenger.ROWSHIFT) + col);
 }
 public void sendInvalid() throws IOException{
	 
 }
 public void sendWin(int winningPlayer) throws IOException { 
	 out.writeInt( Messenger.WIN + winningPlayer);
 }

 public int getMessage() {
	 return message;
 }


 public static void main(String[] args) {
	

 }


 
}
