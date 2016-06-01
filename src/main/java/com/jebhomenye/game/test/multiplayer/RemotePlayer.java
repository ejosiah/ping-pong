package com.jebhomenye.game.test.multiplayer;

import com.jebhomenye.game.test.Ball;
import com.jebhomenye.game.test.HumanPlayer;

/**
 * Created by Josiah on 6/1/2016.
 */
public class RemotePlayer extends HumanPlayer {


    public RemotePlayer(Position direction, RemoteMainScene scene) {
        super(direction, scene);
    }


    @Override
    public int handleCollision(Ball ball) {
        CollidedWithBall collided = scene().getGameEvent();
        return collided.isCollided() ? 1 : 0;
    }

    protected RemoteMainScene scene(){
        return (RemoteMainScene)scene;
    }


}
