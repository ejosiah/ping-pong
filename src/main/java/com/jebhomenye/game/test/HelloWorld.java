package com.jebhomenye.game.test;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jebhomenye.core.GameCore;
import com.jebhomenye.core.Scene;

public class HelloWorld extends GameCore implements KeyListener {


	public HelloWorld(Scene startingScene) {
		super(startingScene);
	}

	@Override
	protected void init(){
		super.init();
		screen.addKeyListener(this);
	}
	
	@Override
	protected void draw(Graphics2D g) {
		g.setColor(screen.background());
		g.fillRect(0, 0, screen.width(), screen.height());
		
		g.setColor(screen.foreground());
		g.drawString("Hello World!", 50, 50);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			stop();
			System.out.println("Stopped");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String...args){
		new HelloWorld(null).run();
	}
	
}
