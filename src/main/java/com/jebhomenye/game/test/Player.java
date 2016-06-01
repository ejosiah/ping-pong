package com.jebhomenye.game.test;

import com.jebhomenye.math.Circle;

import java.awt.*;

/**
 * Created by jay on 27/05/2016.
 */
public interface Player extends GameObject{

	java.util.List<Circle> bounds();

	default void setBall(Ball ball){

	}

	int width();

	int height();

	void init();

	default void checkGameInput(){

	}

	boolean facingRight();

	void update(long elapsedTime);

	void draw(Graphics2D g);

	boolean facingLeft();

	int handleCollision(Ball ball);

	void createBounds();
}
