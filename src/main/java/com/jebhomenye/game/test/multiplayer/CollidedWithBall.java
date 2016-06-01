package com.jebhomenye.game.test.multiplayer;

/**
 * Created by Josiah on 6/1/2016.
 */
public class CollidedWithBall implements GameEvent {
    private boolean collided;

    public CollidedWithBall(boolean collided) {
        this.collided = collided;
    }

    public CollidedWithBall() {
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
}
