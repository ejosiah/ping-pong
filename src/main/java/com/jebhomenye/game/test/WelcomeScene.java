package com.jebhomenye.game.test;

import com.jebhomenye.core.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by jay on 26/05/2016.
 */
public class WelcomeScene extends Scene {

	private Font bigFont = new Font("Arial", Font.BOLD, 50);
	private Font smallFont = new Font("Arial", Font.BOLD, 15);
	private String welcomeText = "Ping Pong";
	private String commandText = "press enter to start game";
	private int bigFontSize = bigFont.getSize();
	private int smallFontSiz = smallFont.getSize();

	@Override
	protected void init() {
		screen().background(Color.BLACK);
		screen().foreground(Color.WHITE);
	}

	@Override
	protected void update(long elapsedTime) {

	}

	@Override
	protected void draw(Graphics2D g) {
		g.setColor(screen().background());
		g.fillRect(0, 0, screen().width(), screen().height());
		int width = screen().width()/3;
		int height = screen().height()/2;

		g.setColor(screen().foreground());
		g.setFont(bigFont);

		g.drawString(welcomeText, width, height);
		g.setFont(smallFont);
		g.drawString(commandText, width + bigFontSize, height + 25);
	}

	@Override
	protected void checkGameInput() {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e){
		super.keyPressed(e);
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			nextScene(new MainScene());
	}
}
