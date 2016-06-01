package com.jebhomenye.game.test;

import com.jebhomenye.math.Circle;
import com.jebhomenye.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.round;

public class Board implements GameObject {
	public Vector2D pos;
	public Vector2D velocity;
	public int width;
	public int height;
	private final Vector2D temp = new Vector2D();
	java.util.List<Circle> bounds = new ArrayList<>();
	
	public Board(float x, float y, int width, int height){
		this.pos = new Vector2D(x, y);
		this.velocity = new Vector2D();
		this.width = width;
		this.height = height;
	}

	public void createBounds(){
		bounds.clear();
		float r = width/2;
		int n = Math.round(height/(r * 2));
		for(int i = 0; i < n; i++){
			bounds.add(new Circle());
		}
		updateBounds();
	}

	public void updateBounds(){
		float r = width/2;
		float d = 2*r;
		int x = round(pos().x() + width/2);
		int y = round(pos().y() + r);

		for(Circle b : bounds){
			b.set(x, y, r);
			y = round(y + d);
		}
	}
	
	public void update(long elapsedTime){
		temp.copy(velocity).multiplyBy(elapsedTime);
		pos.compAdd(temp);
		bounds.forEach(b -> b.origin.compAdd(temp));
		updateBounds();
	}

	public void draw(Graphics2D g){
		g.fillRect(Math.round(pos.x()), Math.round(pos.y()), width, height);
	}

	@Override
	public String toString(){
		return String.format("Board(x: %s, y: %s, dy: %s, w: %s, h : %s)", pos.x(), pos.y(), velocity.y(), width, height);
	}

	@Override
	public Vector2D pos() {
		return pos;
	}

	@Override
	public Vector2D velocity() {
		return velocity;
	}

	public java.util.List<Circle> bounds(){
		return bounds;
	}
}
