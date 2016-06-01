package com.jebhomenye.game.test;

import com.jebhomenye.game.test.multiplayer.GameEvent;

/**
 * Created by Josiah on 6/1/2016.
 */
public class InitEvent implements GameEvent {
    private boolean isServer;
    private Player.Position position;

    public InitEvent(boolean isServer, Player.Position position) {
        this.isServer = isServer;
        this.position = position;
    }

    public InitEvent() {
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public Player.Position getPosition() {
        return position;
    }

    public void setPosition(Player.Position position) {
        this.position = position;
    }
}
