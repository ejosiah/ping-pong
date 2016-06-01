package com.jebhomenye.game.test;

import com.jebhomenye.core.Scene;
import com.jebhomenye.math.Circle;
import com.jebhomenye.math.Probability;
import com.jebhomenye.math.Vector2D;
import com.jebhomenye.math.Weighted;

import java.awt.*;
import java.util.List;

/**
 * Created by jay on 27/05/2016.
 */
public class AIPlayer extends AbstractPlayer {


	private Vector2D temp = new Vector2D();
	private Weighted<Float> target = new Weighted<>();
	private Weighted<Vector2D> ballVelocity = new Weighted<>();
	private int targetError = 50;
	private int ballVelocityError = 0;
	private boolean targetLocked;
	private boolean bounced;
	private float targetY;
	private Probability miss = new Probability(0);
	public boolean shouldMiss;
	private boolean deubg;
	private Vector2D a = new Vector2D(0, 0.002f);


	public AIPlayer(Scene scene){
		super(scene);
	}

	@Override
	public void update(long elapsedTime) {
		if(!shouldMiss && miss.Occured()){
			targetY = 0;
			shouldMiss = true;
			System.out.println(shouldMiss);
		}
		targetY = ball.pos().y();

		if(shouldMiss){ // decelerate so we can miss the ball
			if(velocity().y() > 0){
				targetY =- height()/2;
			}else{
				targetY += height()/2;
			}
		}


		temp.set(board.pos().x(), targetY);

		Vector2D d = temp.compSubtract(ball.pos());
		Vector2D v = ball.velocity();


		// time = displacement / velocity
		float t = d.compDivide(v).mag();
		temp.set(board.pos().x(), ball.pos().y());
		d = temp.compSubtract(board.pos());

		// velocity = displacement / time
		v = d.divide(t);

		velocity().copy(v);



		if (board.pos.y() <= 0) {	// scene boundary
			board.pos.y(0f);
		//	board.updateBounds();
		} else if (board.pos.y() + board.height >= scene.height()) {
			board.pos.y((float) (scene.height() - board.height));
		//	board.updateBounds();
		}
		board.update(elapsedTime);
		if((facingRight() && ball.pos().x() > pos().x() + 100)
				|| (facingLeft() && ball.pos().x() < pos().x() - 100) && shouldMiss){
			shouldMiss = false;
		}

	}

	private float possibleTarget(Ball ball){
		return target
				.clear()
				.add(ball.pos().y(), 100 - targetError)
				.add(ball.pos().y() + 200, targetError/2)
				.add(ball.pos().y() - 200, targetError/2)
				.shuffle()
				.get();
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
