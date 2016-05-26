package com.jebhomenye.game.test;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jebhomenye.core.GameCore;
import com.jebhomenye.core.Scene;

public class PingPong extends GameCore{


	public PingPong(Scene startingScene) {
		super(startingScene);
	}

	public static void main(String...args){
		new PingPong(new WelcomeScene()).run();
	}

}
