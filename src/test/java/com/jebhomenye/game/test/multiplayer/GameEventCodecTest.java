package com.jebhomenye.game.test.multiplayer;

import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;

/**
 * Created by jay on 02/06/2016.
 */
public abstract class GameEventCodecTest {

	protected BallUpdated ballUpdated;
	protected EmbeddedChannel channel;

	@Before
	public void setup(){
		channel = new EmbeddedChannel(new GameEventDecoder());
		ballUpdated = new BallUpdated(5f, 4f, 0.2f, 0.2f);
	}
}
