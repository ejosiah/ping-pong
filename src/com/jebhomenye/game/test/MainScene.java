package com.jebhomenye.game.test;

import com.jebhomenye.core.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by jay on 26/05/2016.
 */
public class MainScene extends Scene {
	private Ball ball;
	private Board board;

	@Override
	public void init(){
		System.out.println("Intializing main scene");
		ball = new Ball(screen().width()/2, screen().height()/2, 10);
		ball.dx = 0.2f;
		ball.dy = 0.2f;
		board = new Board(20, screen().height()/2, 10, 70);
		board.dy = 0f;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				board.dy = -0.3f;
				break;
			case KeyEvent.VK_DOWN:
				board.dy = 0.3f;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
			board.dy = 0.0f;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void update(long elapsedTime){
		if(ball.x <= 0){
			ball.dx = 0.2f;
		}else if(ball.x + ball.diameter() >= screen().width()){
			ball.dx = -0.2f;
		}
		if(ball.y <= 0){
			ball.dy = 0.2f;
		}else if(ball.y + ball.diameter() >= screen().height()){
			ball.dy = -0.2f;
		}

		if(board.y <= 0){
			board.y = 0f;
		}else if(board.y + board.height >= screen().height()){
			board.y = (float)(screen().height() - board.height);
		}

		ball.update(elapsedTime);
		board.update(elapsedTime);

		handleCollision(ball, board);
	}

	public void handleCollision(Ball ball, Board board){
		if(ball.x <= board.x + board.width
				&& (ball.y > board.y && ball.y < board.y + board.height)){
			ball.dx = 0.2f;
		}
	}

	@Override
	protected void draw(Graphics2D g) {
		g.setColor(screen().background());
		g.fillRect(0, 0, screen().width(), screen().height());

		g.setColor(screen().foreground());
		ball.draw(g);
		board.draw(g);
	}

}
