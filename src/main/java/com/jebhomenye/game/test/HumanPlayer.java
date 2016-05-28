package com.jebhomenye.game.test;

import com.jebhomenye.core.KeyListenerAdapter;
import com.jebhomenye.core.Scene;
import com.jebhomenye.math.Circle;
import com.jebhomenye.math.Vector2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jay on 27/05/2016.
 */
public class HumanPlayer implements Player, KeyListenerAdapter {
	private AtomicBoolean up;
	private AtomicBoolean down;

	private Board board;
	private Scene scene;

	public HumanPlayer(Scene scene){
		this.scene = scene;
	}

	@Override
	public List<Circle> bounds() {
		return null;
	}

	@Override
	public int width() {
		return board.width;
	}

	@Override
	public int height() {
		return board.height;
	}

	@Override
	public void init() {
		up = new AtomicBoolean(false);
		down = new AtomicBoolean(false);
		board = new Board(20, scene.height()/2, 10, 70);
	}

	@Override
	public void checkGameInput() {
		if(up.get()){
			board.velocity.y(-0.3f);
		}else if(down.get()){
			board.velocity.y(0.3f);
		}else{
			board.velocity.y(0.0f);
		}
	}

	@Override
	public void update(long elapsedTime) {
		if(board.pos.y() <= 0){
			board.pos.y(0f);
		}else if(board.pos.y() + board.height >= scene.height()){
			board.pos.y((float)(scene.height() - board.height));
		}
		board.update(elapsedTime);
	}

	@Override
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				if(!up.get()) up.getAndSet(true);
				break;
			case KeyEvent.VK_DOWN:
				if(!down.get()) down.getAndSet(true);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			up.getAndSet(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			down.getAndSet(false);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		board.draw(g);
	}

	@Override
	public boolean facingLeft() {
		return false;
	}

	@Override
	public int handleCollision(Ball ball) {
		return 0;
	}

	@Override
	public boolean facingRight() {
		return false;
	}

	@Override
	public Vector2D pos() {
		return board.pos();
	}

	@Override
	public Vector2D velocity() {
		return board.velocity();
	}
}
