import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import GamePanel.AL;

public class GamePanel extends JPanel implements Runnable {
	
	static final int GAME_WIDTH = 1280;
	static final int GAME_HEIGHT = 720;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 40;
	static final int OBSTACLE_WIDTH = 75;
	static final int OBSTACLE_HEIGHT = 590;
	static final int OBSTACLE_DISTANCE = 130;
	static int finalScore;
	static boolean running = true;
	static boolean start = true;
	static boolean end = false;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Obstacle obstacle1;
	Obstacle obstacle2;
	Obstacle obstacle3;
	Obstacle obstacle4;
	Ball ball;
	Score score;
	Text text;
	
	//Constructor
	GamePanel() {
		newBall();
		newObstacles();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		text = new Text(GAME_WIDTH, GAME_HEIGHT, start, end);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void newBall() {
		ball = new Ball((int)(GAME_WIDTH*0.25), GAME_HEIGHT/2, BALL_DIAMETER, BALL_DIAMETER);
	}
	
	public void newObstacles() {
		random = new Random();
		int randomY1 = random.nextInt(OBSTACLE_HEIGHT)-OBSTACLE_HEIGHT;
		obstacle1 = new Obstacle((GAME_WIDTH/2)-(OBSTACLE_WIDTH/2)+2, randomY1, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, 1);
		obstacle2 = new Obstacle((GAME_WIDTH/2)-(OBSTACLE_WIDTH/2)+2, (randomY1+OBSTACLE_DISTANCE+OBSTACLE_HEIGHT), OBSTACLE_WIDTH, OBSTACLE_HEIGHT, 2);
		int randomY2 = random.nextInt(OBSTACLE_HEIGHT)-OBSTACLE_HEIGHT;
		obstacle3 = new Obstacle(GAME_WIDTH, randomY2, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, 3);
		obstacle4 = new Obstacle(GAME_WIDTH, (randomY2+OBSTACLE_DISTANCE+OBSTACLE_HEIGHT), OBSTACLE_WIDTH, OBSTACLE_HEIGHT, 4);

	}
	
	public void moveObstacles() {
		if(obstacle1.x + OBSTACLE_WIDTH <= 0) {
			int randomY1 = random.nextInt(OBSTACLE_HEIGHT)-OBSTACLE_HEIGHT;
			obstacle1.y = randomY1;
			obstacle2.y = randomY1+OBSTACLE_DISTANCE+OBSTACLE_HEIGHT;
			obstacle1.x = GAME_WIDTH;
			obstacle2.x = GAME_WIDTH;
		}
		if(obstacle3.x + OBSTACLE_WIDTH <= 0) {
			int randomY2 = random.nextInt(OBSTACLE_HEIGHT)-OBSTACLE_HEIGHT;
			obstacle3.y = randomY2;
			obstacle4.y = randomY2+OBSTACLE_DISTANCE+OBSTACLE_HEIGHT;
			obstacle3.x = GAME_WIDTH;
			obstacle4.x = GAME_WIDTH;
		}
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(!end) {
			obstacle1.draw(g);
			obstacle2.draw(g);
			obstacle3.draw(g);
			obstacle4.draw(g);
			ball.draw(g);
			score.draw(g2d);
		}
		text.draw(g, score.finalValue);
	}
	
	public void checkScore() {
		if(!end)
			if(ball.x == obstacle1.x || ball.x == obstacle3.x)
				score.value++;
	}
	
	public void update() {
		if(!end)
			ball.update();
		if(end) {
			newBall();
			newObstacles();
		}
	}
	
	public void move() {
		if(!end && !start) {
			obstacle1.move();
			obstacle2.move();
			obstacle3.move();
			obstacle4.move();
			moveObstacles();
			ball.move();
		}
	}
	
	public boolean checkCollision() {
		if(!end) {
			if(ball.y >= GAME_HEIGHT)
				return true;
			if(ball.intersects(obstacle1))
				return true;
			if(ball.intersects(obstacle2))
				return true;
			if(ball.intersects(obstacle3))
				return true;
			if(ball.intersects(obstacle4))
				return true;
			else
				return false;
		}
		return false;
	}
	
	public void run() {
		//Game Loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				if(checkCollision()) {
					text.end = true;
					score.finalValue = score.value;
					score.value = 0;
				}
				update();
				checkScore();
				move();
				start = text.start;
				end = text.end;
				delta--;
				repaint();
			}
		}
	}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			ball.keyPressed(e);
			obstacle1.keyPressed(e);
			obstacle2.keyPressed(e);
			obstacle3.keyPressed(e);
			obstacle4.keyPressed(e);
			text.keyPressed(e);
		}
		
	}
}
