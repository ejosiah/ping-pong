package com.jebhomenye.game.test.multiplayer;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by Josiah on 6/1/2016.
 */
public class CollidedWithBall implements GameEvent {

	private static final int SIZE = 1;

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

	@Override
	public byte[] toBytes() {
		byte[] header = GameEvent.super.toBytes();
		int offset = header.length;
		ByteBuffer out = ByteBuffer.allocate(offset + SIZE_OF_INT + SIZE);
		out.putInt(SIZE);
		out.put((byte)(collided ? 1 : 0));
		return out.array();
	}

	@Override
	public CollidedWithBall fromBytes(byte[] bytes) {
		byte c = bytes[0];
		if(c == 0){
			collided = false;
		}else if(c == 1){
			collided = true;
		}else{
			throw new RuntimeException("invalid data");
		}
		return this;
	}
}
