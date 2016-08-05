package tictactoe;

/**
 * The virtual representation of the GameBoard
 * @author Blair Sweigman
 *
 */

public class GameBoard {
	private int[][] board;
	public static final  int SIZE = 3;

	/**
	 * Constructor for the game board 3x3 array
	 */
	public GameBoard() {
		board = new int[3][3];
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				board[i][j] = 0;
			}
		}
	}
	/**
	 * @param row the row to mark
	 * @param col the column to mark
	 * @param player the player to mark
	 * @return true if valid move, false if invalid
	 */
	public boolean markBoard(int row, int col,int player){
		if (board[row][col]==0) {
			board[row][col] = player;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Prints the board, used in testing
	 */
	public void printBoard() {
		for (int i = 0;i<SIZE;i++) {
			for (int j= 0; j< SIZE;j++) {
				System.out.print(board[i][j] +"\t");
			}
			System.out.println("");
		}
	}
	/**
	 * checks to see if there is a winner
	 * @return an int > 0 if there is a winner, 0 if no winner
	 */
	public int isWinner() {
		return winRow() + winCol() + winDiag();
	}
	/**
	 * checks rows for winner
	 * @return 1 if winning row found
	 */
	private int winRow() {
		for (int row=0;row<SIZE;row++) {
			if (board[row][0] == board[row][1] && board[row][0] ==board[row][2]){
				return board[row][0];
			}
		}
		return 0;
	}
	/**
	 * checks columns for winner
	 * @return 1 if winnering column found
	 */
	private int winCol() {
		for (int col=0;col<SIZE;col++) {
			if (board[0][col] == board[1][col] && board[0][col] ==board[2][col]){
				return board[0][col];
			}
		}
		return 0;
	}
	/**
	 * Checks the diagonals if a win is found
	 * @return 1 if winning diagonal found
	 */
	private int winDiag () {
		if (board[0][0] == board[1][1] && board[1][1]==board[2][2])
			return board[1][1];
		else if  (board[2][0]==board[1][1] && board[1][1]==board[0][2])
			return board[1][1];
		else return 0;
	}
	
	/**
	 * Checks to see if there are no moves left
	 * @return true if  all boxes full, false if empty box found
	 */
	public boolean NoMoves() {
		//boolean check = true;
		for (int i = 0;i<SIZE;i++) {
			for (int j= 0; j< SIZE;j++) {
				if (board[i][j]==0) return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks for a Draw
	 * @return true if no winner and all squares are filled
	 */
	public boolean isDraw() {
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
