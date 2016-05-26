package com.jebhomenye.game.test;

import java.awt.Graphics2D;

public class Ball {
	public float dx;
	public float dy;
	public float x;
	public float y;
	public int radius;
	
	public Ball(float x, float y, int radius){
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public int diameter(){
		return radius * 2;
	}
	
	public void update(long elapsedTime){
		x += dx * elapsedTime;
		y += dy * elapsedTime;
	}
	
	public void draw(Graphics2D g){
		g.fillOval(Math.round(x), Math.round(y), diameter(), diameter());
	}

	@Override
	public String toString() {
		return "Ball{" +
				"dx=" + dx +
				", dy=" + dy +
				", x=" + x +
				", y=" + y +
				", radius=" + radius +
				'}';
	}
}
