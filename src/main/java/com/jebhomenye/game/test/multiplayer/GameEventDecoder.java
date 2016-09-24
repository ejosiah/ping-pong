package com.jebhomenye.game.test.multiplayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * Created by jay on 02/06/2016.
 */
public class GameEventDecoder extends ByteToMessageDecoder {

	enum State{HEADER, BODY}

	private State state;
	private String className;
	private int headerSize;
	private int bodySize;

	public GameEventDecoder(){
		reset();
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		switch (state){
			case HEADER:
				if(in.readableBytes() < 4){
					return;
				}else if(headerSize < 0) {
					headerSize = in.readInt();
				}
				if(in.readableBytes() < headerSize){
					return;
				}
				byte[] type = new byte[headerSize];
				in.readBytes(type);
				className = new String(type);
				state = State.BODY;
				break;
			case BODY:
				if(in.readableBytes() < 4){
					return;
				}else if(bodySize < 0) {
					bodySize = in.readInt();
				}
				if(in.readableBytes() < bodySize){
					return;
				}
				GameEvent event = (GameEvent)Class.forName(className).newInstance();
				byte[] body = new byte[bodySize];
				in.readBytes(body);
				event.fromBytes(body);
				out.add(event);
				reset();
				break;

		}
	}

	private void reset(){
		state = State.HEADER;
		className = null;
		headerSize = -1;
		bodySize = -1;
	}
}
