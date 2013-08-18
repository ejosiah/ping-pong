package com.jebhomenye.core;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jebhomenye.graphics.Screen;

public abstract class GameCore {
	private static DisplayMode[] POSSIBLE_MODES = {
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 32, 0)
	};
	
	protected Screen screen;
	private AtomicBoolean isRunning;
	
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			screen.restoreScreen();
		}
	}
	
	protected void init(){
		isRunning = new AtomicBoolean(true);
		screen = new Screen();
		screen.fullScreenWindow(screen.firstCompatibleMode(POSSIBLE_MODES));
		screen.background(Color.BLACK);
		screen.foreground(Color.WHITE);
		screen.font(new Font("Dialog", Font.PLAIN, 20));
	}
	
	public void gameLoop(){
		final long startTime = System.currentTimeMillis();
		long currentTime = startTime;
		while(isRunning.get()){
			long elapsedTime = System.currentTimeMillis() - currentTime;
			currentTime += elapsedTime;
			update(elapsedTime);
			
			Graphics2D g = screen.graphics();
			draw(g);
			g.dispose();
			
			screen.update();
		}
	}
	
	public void stop(){
		isRunning.set(false);
	}
	
	
	protected  void update(long elapsedTime){
		// do nothing subclasses should override
	}
	
	protected abstract void draw(Graphics2D g);
}
