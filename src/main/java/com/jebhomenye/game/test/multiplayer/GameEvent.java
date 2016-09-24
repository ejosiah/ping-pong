package com.jebhomenye.game.test.multiplayer;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by Josiah on 6/1/2016.
 */
public interface GameEvent extends Serializable{

	int SIZE_OF_INT = 4;

	default byte[] toBytes(){
		String name = this.getClass().getName();
		int headerSize = name.length() + SIZE_OF_INT;
		ByteBuffer buff = ByteBuffer.allocate(headerSize);
		buff.putInt(name.length());
		buff.put(name.getBytes());

		return buff.array();
	}

	GameEvent fromBytes(byte[] bytes);
}
