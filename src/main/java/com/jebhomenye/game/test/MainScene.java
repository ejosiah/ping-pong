package com.jebhomenye.game.test;
import static java.lang.Math.*;
import com.jebhomenye.core.Scene;
import com.jebhomenye.math.Vector2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by jay on 26/05/2016.
 */
public class MainScene extends Scene {
	protected Ball ball;
	protected Player player1;
	protected Player player2;
	protected java.util.List<Player> players = new ArrayList<>();
	protected int hits;
	protected Vector2D v = new Vector2D(0.05f);
	protected Vector2D maxV = new Vector2D(0.5f);
	protected Vector2D ballDirection = new Vector2D();
	protected Vector2D temp = new Vector2D();


	@Override
	public void init(){
		System.out.println("Intializing main scene");
		ball = new Ball(screen().width()/2, screen().height()/2, 10, this);
		player1 = new AIPlayer(Player.Position.LEFT, this);
		player2 = new AIPlayer(Player.Position.RIGHT, this);
	//	player1 = new HumanPlayer(this);
		players.add(player1);
		players.add(player2);

		players.forEach( player -> {
			player.setBall(ball);
			player.init();
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);

		players.forEach((player) -> {
			if(player instanceof KeyListener){
				((KeyListener) player).keyPressed(e);
			}
		});
	}

	@Override
	public void keyReleased(KeyEvent e) {
		players.forEach((player) -> {
			if(player instanceof KeyListener){
				((KeyListener) player).keyReleased(e);
			}
		});
	}

	@Override
	public void update(long elapsedTime){
		updateBall(elapsedTime);
		updatePlayers(elapsedTime);
		players.forEach( player -> handleCollision(ball, player));
		adjustBallSpeed();
	}

	protected void updateBall(long elapsedTime){
		if(ball.hitLeftBoundary()){
			ball.velocity().x(abs(ball.velocity().x()));
		}else if(ball.hitRightBoundary()){
			ball.velocity().x(-ball.velocity().x());
		}
		if(ball.pos().y() <= 0){
			ball.velocity().y(abs(ball.velocity().y()));
		}else if(ball.pos().y() + ball.diameter() >= screen().height()){
			ball.velocity().y(-ball.velocity().y());
		}

		ball.update(elapsedTime);
	}

	protected void updatePlayers(long elapsedTime){
		players.forEach(player -> {
			player.update(elapsedTime);

		});
	}

	protected void adjustBallSpeed(){
		if(hits > 0 && hits%2 == 0){
			ballDirection = Vector2D.direction(ballDirection.copy(ball.velocity()));
			temp = Vector2D.abs(temp.copy(ball.velocity())).compAdd(v).compMultiply(ballDirection);

			ball.velocity().set(temp.x(), temp.y());
			//	System.out.println("ball velocity: " + ball.velocity());
			hits = 0;
		}
	}

	public void handleCollision(Ball ball, Player player){
		hits += player.handleCollision(ball);
	}

	@Override
	protected void draw(Graphics2D g) {
		g.setColor(screen().background());
		g.fillRect(0, 0, screen().width(), screen().height());

		g.setColor(screen().foreground());
		ball.draw(g);
		players.forEach(player -> player.draw(g));
	}

	@Override
	protected void checkGameInput() {
		players.forEach(Player::checkGameInput);
	}

}
