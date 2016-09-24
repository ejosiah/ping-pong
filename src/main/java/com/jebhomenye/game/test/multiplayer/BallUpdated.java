package com.jebhomenye.game.test.multiplayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.nio.ByteBuffer;

/**
 * Created by Josiah on 6/1/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BallUpdated implements GameEvent {

	public static final int SIZE = 16;

    private float x, y, dx, dy;

	@Override
	public byte[] toBytes() {
		byte[] header = GameEvent.super.toBytes();
		int offset = header.length;
		ByteBuffer buff = ByteBuffer.allocate(offset + SIZE_OF_INT + SIZE);
		buff.put(header);
		buff.putInt(SIZE);
		buff.putFloat(x);
		buff.putFloat(y);
		buff.putFloat(dx);
		buff.putFloat(dy);

		return buff.array();
	}

	@Override
	public BallUpdated fromBytes(byte[] bytes) {
		ByteBuffer in = ByteBuffer.wrap(bytes);
		x = in.getFloat();
		y = in.getFloat();
		dx = in.getFloat();
		dy = in.getFloat();
		return this;
	}
}
