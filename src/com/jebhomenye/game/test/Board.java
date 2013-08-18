package com.jebhomenye.game.test;

import java.awt.Graphics2D;

public class Board {
	public Float dy;
	public Float x;
	public Float y;
	public int width;
	public int height;
	
	public Board(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update(long elapsedTime){
		y += dy * elapsedTime;
	}
	
	public void draw(Graphics2D g){
		g.fillRect(Math.round(x), Math.round(y), width, height);
	}
}
