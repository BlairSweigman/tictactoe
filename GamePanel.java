package tictactoe;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class GamePanel extends JPanel {
	private String toDraw;
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
		toDraw = "";
	}
	public void setPanel(String inDraw) {
		
		toDraw = inDraw;
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
