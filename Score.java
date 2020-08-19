import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int value = 0;
	int finalValue;
	
	Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Comic Sans", Font.BOLD, 60));
		
		g.drawString(String.valueOf(value), 12, 62);
	}
}
