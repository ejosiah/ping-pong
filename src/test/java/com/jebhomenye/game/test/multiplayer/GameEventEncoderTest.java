package com.jebhomenye.game.test.multiplayer;

import static org.junit.Assert.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jay on 02/06/2016.
 */
public class GameEventEncoderTest {

	protected BallUpdated ballUpdated;
	protected EmbeddedChannel channel;

	@Before
	public void setup(){
		channel = new EmbeddedChannel(new GameEventEncoder());
		ballUpdated = new BallUpdated(5f, 4f, 0.2f, 0.2f);
	}

	@Test
	public void testEncode(){
		channel.writeOutbound(ballUpdated);

		ByteBuf outbound = channel.readOutbound();

		int skip = GameEvent.SIZE_OF_INT * 2 + BallUpdated.class.getName().length();
		outbound.skipBytes(skip);

		byte[] data = new byte[outbound.readableBytes()];
		outbound.readBytes(data);

		BallUpdated expected = ballUpdated;
		BallUpdated actual = new BallUpdated().fromBytes(data);

		assertEquals(expected, actual);
	}
}
