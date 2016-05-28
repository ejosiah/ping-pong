package com.jebhomenye.math;

/**
 * Created by jay on 27/05/2016.
 */
public class Circle {
	public Vector2D origin;
	public float r;

	public Circle(){
		this(0, 0, 0);
	}

	public Circle set(float x, float y, float r){
		this.r = r;
		origin.set(x, y);
		return this;
	}

	public Circle(float x, float y, float r){
		this.origin = new Vector2D(x, y);
		this.r = r;
	}

	public boolean collidesWith(Circle other){
		float d = Vector2D.distance(origin, other.origin);
		return d <= (this.r + other.r);
	}

	@Override
	public String toString(){
		return String.format("Circle(%s, %s, %s)", origin.x(), origin.y(), r);
	}

}
