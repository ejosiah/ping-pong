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
public class HumanPlayer extends AbstractPlayer implements KeyListenerAdapter {
	private AtomicBoolean up;
	private AtomicBoolean down;

	public HumanPlayer(Scene scene) {
		super(scene);
	}


	@Override
	public void init() {
		super.init();
		up = new AtomicBoolean(false);
		down = new AtomicBoolean(false);
	}

	@Override
	public void checkGameInput() {
		if (up.get()) {
			board.velocity.y(-0.3f);
		} else if (down.get()) {
			board.velocity.y(0.3f);
		} else {
			board.velocity.y(0.0f);
		}
	}

	@Override
	public void update(long elapsedTime) {
		if (board.pos.y() <= 0) {
			board.pos.y(0f);
		} else if (board.pos.y() + board.height >= scene.height()) {
			board.pos.y((float) (scene.height() - board.height));
		}
		board.update(elapsedTime);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (!up.get()) up.getAndSet(true);
				break;
			case KeyEvent.VK_DOWN:
				if (!down.get()) down.getAndSet(true);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up.getAndSet(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down.getAndSet(false);
		}
	}
}
