package com.jebhomenye.game.test;

import com.jebhomenye.core.Scene;
import com.jebhomenye.math.Circle;
import com.jebhomenye.math.Vector2D;

import java.awt.Graphics2D;

public class Ball implements GameObject {
	private final Scene scene;
	public int radius;
	private Vector2D pos;
	private Vector2D velocity;
	private Vector2D temp = new Vector2D();
	private Circle bounds;
	
	public Ball(float x, float y, int radius, Scene scene){
		int r = radius;
		pos = new Vector2D(x, y);
		velocity = new Vector2D(0.2f, 0.2f);
		this.radius = radius;
		bounds = new Circle(x + r, y + r, r);
		this.scene = scene;
	}
	
	public int diameter(){
		return radius * 2;
	}
	
	public void update(long elapsedTime){
		temp.copy(velocity).multiplyBy(elapsedTime);
		pos.compAdd(temp);
		bounds.origin.compAdd(temp);

	}
	
	public void draw(Graphics2D g){
		g.fillOval(Math.round(pos.x()), Math.round(pos.y()), diameter(), diameter());
	}

	@Override
	public String toString() {
		return String.format("Ball(pos: %s, velocity: %s", pos, velocity);
	}

	@Override
	public Vector2D pos() {
		return pos;
	}

	@Override
	public Vector2D velocity() {
		return velocity;
	}

	public Circle bounds(){
		return bounds;
	}

	public boolean hitLeftBoundary(){
		return pos().x() <= 0;
	}

	public boolean hitRightBoundary(){
		return pos().x() + diameter() >= scene.width();
	}
}
