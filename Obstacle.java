import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Obstacle extends Rectangle {
	
	int id;
	int xVelocity = 0;
	
	Obstacle(int x, int y, int width, int height, int id) {
		super(x, y, width, height);
		this.id = id;
	}
	
	public void move() {
		x -= xVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			xVelocity = 3;
	}
}
