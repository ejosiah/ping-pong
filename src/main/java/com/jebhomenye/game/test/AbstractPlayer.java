package com.jebhomenye.game.test;

import static java.lang.Math.*;
import com.jebhomenye.core.Scene;
import com.jebhomenye.math.Circle;
import com.jebhomenye.math.Vector2D;

import java.awt.*;
import java.util.List;

/**
 * Created by jay on 28/05/2016.
 */
public abstract class  AbstractPlayer implements Player {
	protected Ball ball;
	protected Board board;
	protected Scene scene;
	protected boolean deubg;


	public AbstractPlayer(Scene scene){
		this.scene = scene;
	}

	@Override
	public List<Circle> bounds() {
		return board.bounds();
	}

	@Override
	public void setBall(Ball ball){
		this.ball = ball;
	}

	@Override
	public int width() {
		return board.width;
	}

	@Override
	public int height() {
		return board.height;
	}

	@Override
	public void init() {
		board = new Board(20, scene.height()/2, 10, 70);
		createBounds();
	}

	public void createBounds() {
		board.createBounds();
	}

	@Override
	public boolean facingRight(){
		return pos().x() < scene.width()/2;
	}

	@Override
	public boolean facingLeft() {
		return !facingRight();
	}

	@Override
	public int handleCollision(Ball ball) {
		final Player player = this;
		boolean collisionOccured = player.bounds().stream().anyMatch(b -> ball.bounds().collidesWith(b) );
		if(collisionOccured){
			if(player.facingRight() && ball.velocity().x() < 0){
				ball.velocity().x(abs(ball.velocity().x()));
				//	System.out.println("Collision left");
				return 1;
			}else if(player.facingLeft() && ball.velocity().x() > 0){
				ball.velocity().x(-ball.velocity().x());
			//	System.out.println("Collision right");
				return 1;
			}
		}
		return 0;

	}

	@Override
	public void draw(Graphics2D g) {
		board.draw(g);
		Color oldColor = g.getColor();
		if(deubg) {
			bounds().forEach(b -> {
				int x = Math.round(b.origin.x() - b.r);
				int y = Math.round(b.origin.y() - b.r);
				int d = Math.round(b.r * 2);
				g.setColor(Color.RED);
				g.drawOval(x, y, d, d);
			});
			g.setColor(oldColor);
		}
	}

	@Override
	public Vector2D pos() {
		return board.pos();
	}

	@Override
	public Vector2D velocity() {
		return board.velocity();
	}
}
