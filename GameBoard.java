package tictactoe;

public class GameBoard {
	private int[][] board;
	public static final  int SIZE = 3;
 
	public GameBoard() {
		board = new int[3][3];
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
			 board[i][j] = 0;
			}
		}
	}
 public boolean markBoard(int row, int col,int player){
	if (board[row][col]==0) {
		board[row][col] = player;
		return true;
	}
	else {
		return false;
	}
 }
 public void printBoard() {
	 for (int i = 0;i<SIZE;i++) {
		 for (int j= 0; j< SIZE;j++) {
			 System.out.print(board[i][j] +"\t");
		 }
		 System.out.println("");
	 }
 }
 public int isWinner() {
	 return winRow() + winCol() + winDiag();
 }
 private int winRow() {
	 for (int row=0;row<SIZE;row++) {
		 if (board[row][0] == board[row][1] && board[row][0] ==board[row][2]){
			 return board[row][0];
		 }
	 }
	 return 0;
 }
 private int winCol() {
	 for (int col=0;col<SIZE;col++) {
		 if (board[0][col] == board[1][col] && board[0][col] ==board[2][col]){
			 return board[0][col];
		 }
	 }
	 return 0;
 }
 private int winDiag () {
	 if (board[0][0] == board[1][1] && board[1][1]==board[2][2])
		  return board[1][1];
	 else if  (board[2][0]==board[1][1] && board[1][1]==board[0][2])
		  return board[1][1];
	 else return 0;
 }
 public boolean NoMoves() {
	 //boolean check = true;
	 for (int i = 0;i<SIZE;i++) {
		 for (int j= 0; j< SIZE;j++) {
			 if (board[i][j]==0) return false;
		 }
	 }
	 return true;
 }
 public boolean isCatsGame() {
	 return (isWinner()==0&&NoMoves());
 }
 public static void main(String args[]) {
	 GameBoard myBoard = new GameBoard();
	 myBoard.markBoard(0, 0,1);
	 myBoard.markBoard(1, 2,2);
	 myBoard.markBoard(1, 1, 1);
	 myBoard.markBoard(1, 0,2);
	 myBoard.markBoard(2, 2,1);
	 System.out.println(myBoard.isWinner());
	 myBoard.printBoard();
	 
 }
}
