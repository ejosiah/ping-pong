package com.jebhomenye.game.test.multiplayer;
import static org.junit.Assert.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jay on 02/06/2016.
 */
public class GameEventDecoderTest {

	private BallUpdated ballUpdated;
	private EmbeddedChannel channel;

	private Object obj = new Object();
	private List<String> list = new ArrayList<>(Arrays.asList("Hello", "World"));

	@Before
	public void setup(){
		channel = new EmbeddedChannel(new GameEventDecoder());
		ballUpdated = new BallUpdated(5f, 4f, 0.2f, 0.2f);
	}

	@Test
	public void testEncodeGameEvent(){
		ByteBuf buf = Unpooled.copiedBuffer(ballUpdated.toBytes());
		assertTrue(channel.writeInbound(buf));

		GameEvent event = channel.readInbound();
		System.out.println(list);
		assertEquals(ballUpdated, event);
		list.add("Hey");
		System.out.println(obj);
	}

	@Test
	public void testEncodeGameEventFragments(){
		 byte[] data = ballUpdated.toBytes();
		int fragSize = data.length/3;
		ByteBuf buffer = Unpooled.copiedBuffer(data);

		ByteBuf fragment1 = buffer.readBytes(fragSize);
		ByteBuf fragment2 = buffer.readBytes(fragSize);
		ByteBuf fragment3 = buffer;

		assertFalse(channel.writeInbound(fragment1));
		assertFalse(channel.writeInbound(fragment2));
		assertTrue(channel.writeInbound(fragment3));

		GameEvent event = channel.readInbound();

		assertEquals(ballUpdated, event);
		System.out.println(list);
		list.add("Changed");
		list.add("here");
		System.out.println(obj);

	}
}
