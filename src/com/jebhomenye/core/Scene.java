package com.jebhomenye.core;


import com.jebhomenye.graphics.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by jay on 26/05/2016.
 */
public abstract class Scene implements KeyListener{

	private GameCore gameCore;

	public void set(GameCore gameCore){
		this.gameCore = gameCore;

	}

	protected void nextScene(Scene scene){
		gameCore.nextScene(scene);
	}

	protected abstract void init();

	protected abstract void update(long elapsedTime);

	protected abstract void draw(Graphics2D g);

	protected Screen screen(){
		return gameCore.screen;
	}

	protected void stop(){
		gameCore.stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			stop();
	}


	public void dispose(){
		gameCore = null;
	}
}
