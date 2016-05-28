package com.jebhomenye.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by jay on 27/05/2016.
 */
public interface KeyListenerAdapter extends KeyListener {

	@Override
	default void keyTyped(KeyEvent e) {

	}

	@Override
	default void keyPressed(KeyEvent e) {

	}

	@Override
	default void keyReleased(KeyEvent e) {

	}
}
