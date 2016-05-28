package com.jebhomenye.core;


import com.jebhomenye.graphics.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jay on 26/05/2016.
 */
public abstract class Scene implements KeyListenerAdapter{

	private GameCore gameCore;
	protected AtomicBoolean exit = new AtomicBoolean(false);

	public void set(GameCore gameCore){
		this.gameCore = gameCore;
	}

	final void run(long elapsedTime, Graphics2D g){
		if(exit.get()){
			stop();
			return;
		}
		checkGameInput();
		update(elapsedTime);
		draw(g);
	}

	protected void nextScene(Scene scene){
		gameCore.nextScene(scene);
	}

	protected abstract void init();

	protected abstract void update(long elapsedTime);

	protected abstract void draw(Graphics2D g);

	protected abstract void checkGameInput();

	protected Screen screen(){
		return gameCore.screen;
	}

	protected void stop(){
		gameCore.stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			exit.getAndSet(true);
	}


	public void dispose(){
		gameCore = null;
	}

	public int width(){
		return gameCore.screen.width();
	}

	public int height(){
		return gameCore.screen.height();
	}
}
