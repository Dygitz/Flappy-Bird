import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Text {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	static boolean start;
	static boolean end;
	
	Font font = new Font("Arial", Font.BOLD, 60);
	
	//Constructor
	Text(int GAME_WIDTH, int GAME_HEIGHT, boolean start, boolean end) {
		Text.GAME_WIDTH = GAME_WIDTH;
		Text.GAME_HEIGHT = GAME_HEIGHT;
		Text.start = start;
		Text.end = end;
	}
	
	public void draw(Graphics g, int finalScore) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.white);
		g2d.setFont(font);
		
		
		
		if(start) {
			g2d.drawString(String.valueOf("Press Up Arrow"), (int)(GAME_WIDTH*0.6), (GAME_HEIGHT/2));
			g2d.drawString(String.valueOf("To Start"), (int)(GAME_WIDTH*0.69), (GAME_HEIGHT/2+60));
		}
		if(end) {
			drawCenteredText("Press Space To", GAME_WIDTH, GAME_HEIGHT, g);
			drawCenteredText("Try Again", GAME_WIDTH, GAME_HEIGHT+120, g);
			drawCenteredText("Score: "+finalScore, GAME_WIDTH, GAME_HEIGHT-160, g);
		}
	}
	
	public void drawCenteredText(String s, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			if(start)
				start = false;
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			if(end) {
				end = false;
				start = true;
			}
				
	}
}
