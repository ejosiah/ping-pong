package com.jebhomenye.game.test.multiplayer;

import akka.actor.UntypedActor;
import akka.protobuf.ByteString;

/**
 * Created by Josiah on 6/1/2016.
 */
public class Codec extends UntypedActor {

    private GameEventQueue in;
    private GameEventQueue out;

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof GameEvent){
            ByteString encodedMsg = encode((GameEvent)message);
        }else if(message instanceof ByteString){
            GameEvent event = decode((ByteString)message);
            in.put(event);
        }
    }

    private GameEvent decode(ByteString message) {
        return null;
    }

    private ByteString encode(GameEvent gameEvent) {
        return null;
    }
}
