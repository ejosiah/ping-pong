package com.jebhomenye.game.test.multiplayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by jay on 02/06/2016.
 */
public class GameEventEncoder extends MessageToByteEncoder<GameEvent> {

	@Override
	protected void encode(ChannelHandlerContext ctx, GameEvent msg, ByteBuf out) throws Exception {
		out.writeBytes(msg.toBytes());
	}
}
