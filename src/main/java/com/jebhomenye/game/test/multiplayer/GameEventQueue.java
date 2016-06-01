package com.jebhomenye.game.test.multiplayer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Josiah on 6/1/2016.
 */
public class GameEventQueue extends ArrayBlockingQueue<GameEvent> {

    public GameEventQueue(int capacity) {
        super(capacity);
    }
}
