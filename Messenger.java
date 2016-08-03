package tictactoe;

public class Messenger {
	 public static final int START = 0x10;
	 public static final int X = 0x20;
	 public static final int O = 0x40;
	 public static final int INVALID =0x80;
	 public static final int WIN =0x100;
	 
	 public static final int ROWMASK = 0xC;
	 public static final int COLMASK = 0x3;
	 public static final int DATAMASK = 0xF;
	 public static final int CODEMASK = 0xfff0;
	 public static final int ROWSHIFT = 0x2;
	 private int message;
	
	 public Messenger(int mess) {
		 message = mess;
	 }
	 public int getMessage() {
		 return message;
	 }
	 public int getData() {
		 return message & DATAMASK;
	 }
	 public int getCode() {
		 return  message & CODEMASK;
	 }
	 public  int getRow() {
		 return (getData() & ROWMASK) >>> ROWSHIFT;
	 }
	 public  int getCol() {
		 return (getData() & COLMASK);
	 }
	 public String getLetter() {
		 if ((getCode() & X)==X) {
			 return  "X";
		 }
		 else if ((getCode() & O)==O) {
			 return "O";
		 }
		 else return "";
	 }
	 public boolean isStartMsg() {
		 if((message & START)==START) {
			 return true;
		 }
		 else return false;
	 }
	 public boolean isWinMsg() {
		 if((message & WIN)==WIN) {
			 return true;
		 }
		 else return false;
	 }
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
