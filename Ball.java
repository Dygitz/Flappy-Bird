import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
	
	float yVelocity;
	float accel;
	
	//Constructor
	Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		yVelocity = 0f;
		accel = 0.0f;
	}
	
	public void jump() {
		yVelocity = -6;
	}
	
	public void update() {
		if(yVelocity <= 10)
			yVelocity += accel;
	}
	
	public void move() {
		y += yVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, height, width);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP)
			accel = 0.4f;
			jump();
	}
}
