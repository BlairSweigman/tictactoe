package tictactoe;

public class Player {
	private int playerID;
	private int wins;
	private int losses;
	private char letter;
	public  Player(int playerID2,char l) {
		playerID = playerID2;
		letter = l;
		wins = 0;
		losses = 0;
	}
	public Player(int pid) {
		this(pid,pid==1?'X':'O');
	}
	public void setPlayerID(int pid) {
		playerID = pid;
	}
	public void setLetter(char l) {
		letter = l;
	}
	public void addWin() {
		wins++;
	}
	public void addLoss() {
		losses++;
	}
	public int getPlayerID() {
		return playerID;
	}
	public char getLetter() {
		return letter;
	}
	public int getWin() {
		return wins;
	}
	public int getLoss() {
		return losses;
	}
}
