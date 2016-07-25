package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class GamePanel extends JButton {
	private String toDraw;
	private int row;
	private int col;
	private boolean waiting;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     JFrame f = new JFrame();
     f.setTitle("Panel Test");
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f.setSize(100,100);
     GamePanel p = new GamePanel();
     f.add(p);
    
     f.setVisible(true);
     p.setPanel("O");
     p.repaint();
	}
	public GamePanel() {
		setSize(200,200);
		setOpaque(true);
		setBackground(Color.WHITE);
		toDraw = "";
	}
	public void setPanel(String inDraw) {
		
		toDraw = inDraw;
	}
	public void setLoc(int row, int col){
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
		
	}
	public int getCol() {
		return col;
	}
	 public boolean equals(GamePanel g) {
		 if((g.getRow()==row )&&(g.getCol()==col)) {
			 return true;
		 }
		 else return false;
	 }
	 public void setWaiting(boolean w) {
		 waiting = w;
	 }
	 public boolean isWaiting() {
		 return waiting;
	 }
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (toDraw=="X") {
			g2.drawLine(0, 0, 100, 100);
			g2.drawLine(0, 100, 100, 0);
		}
		else if (toDraw=="O") {
			g2.drawOval(25, 25, 50, 50);
		}
		else {
			g2.dispose();
		}
	}
}
