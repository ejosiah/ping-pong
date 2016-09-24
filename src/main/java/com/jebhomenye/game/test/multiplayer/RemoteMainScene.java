package com.jebhomenye.game.test.multiplayer;

import com.jebhomenye.game.test.*;

import static java.lang.Math.abs;

/**
 * Created by Josiah on 6/1/2016.
 */
public class RemoteMainScene extends MainScene {

    private boolean isServer;
    private GameEventQueue inEventQueue;
    private GameEventQueue outQueue;

    public RemoteMainScene(GameEventQueue in, GameEventQueue out){
        this.inEventQueue = in;
        this.outQueue = out;
    }

    @Override
    public void init(){
        InitEvent initEvent = getGameEvent();

        isServer = initEvent.isServer();

        if(isServer){
            ball = new Ball(screen().width()/2, screen().height()/2, 10, this);
            player1 = new HumanPlayer(Player.Position.LEFT, this);
            player2 = new RemotePlayer(Player.Position.RIGHT, this);
            sendGameEvent(new BallUpdated(ball.pos().x(), ball.pos().y(), ball.velocity().x(), ball.velocity().y()));
        }else{
            BallUpdated ballUpdated = getGameEvent();
            ball = new Ball(ballUpdated.getX(), ballUpdated.getY(), 10, this);
            ball.velocity().set(ballUpdated.getDx(), ballUpdated.getDy());
            player1 = new HumanPlayer(Player.Position.RIGHT, this);
            player2 = new RemotePlayer(Player.Position.LEFT, this);
        }

        players.add(player1);
        players.add(player2);

        players.forEach( player -> {
            player.setBall(ball);
            player.init();
        });
    }


    @Override
    protected void updateBall(long elapsedTime){
        if(this.isServer) {
            super.updateBall(elapsedTime);
            sendGameEvent(new BallUpdated(ball.pos().x(), ball.pos().y(), ball.velocity().x(), ball.velocity().y()));
        }else{
            BallUpdated ballUpdated = getGameEvent();
            ball.pos().set(ballUpdated.getX(), ballUpdated.getY());
            ball.velocity().set(ballUpdated.getDx(), ballUpdated.getDy());
        }
    }

    @SuppressWarnings("unchecked")
    protected  <T extends GameEvent> T getGameEvent() {
        GameEvent event = null;
        try {
            event = inEventQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return (T)event;
    }

    protected void sendGameEvent(GameEvent gameEvent){
        try{
            outQueue.put(gameEvent);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    protected void updatePlayers(long elapsedTime){
        super.updatePlayers(elapsedTime);
    }

    protected void adjustBallSpeed(){
        super.adjustBallSpeed();
    }

    public void handleCollision(Ball ball, Player player){
        int hit  = player.handleCollision(ball);
        if(hit > 0){
            sendGameEvent(new CollidedWithBall(true));
            sendGameEvent(new BallUpdated(ball.pos().x(), ball.pos().y(), ball.velocity().x(), ball.velocity().y()));
            hits += hit;
        }else{
            CollidedWithBall collidedWithBall = getGameEvent();
            BallUpdated ballUpdated = getGameEvent();
            ball.pos().set(ballUpdated.getX(), ballUpdated.getY());
            ball.velocity().set(ballUpdated.getDx(), ballUpdated.getDy());
            hits += collidedWithBall.isCollided() ? 1 : 0;
        }
    }

}
