package com.jebhomenye.game.test.multiplayer;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.japi.Creator;

import java.net.InetSocketAddress;


/**
 * Created by Josiah on 6/1/2016.
 */
public class Client extends UntypedActor {

    private final InetSocketAddress remote;
    private final ActorRef listener;

    public static Props props(InetSocketAddress remote, ActorRef listener){
        return Props.create(Client.class, remote, listener);
    }

    public Client(InetSocketAddress remote, ActorRef listener) {
        this.remote = remote;
        this.listener = listener;

        ActorRef tcp = Tcp.get(getContext().system()).manager();
        tcp.tell(TcpMessage.connect(remote), getSelf());
    }

    @Override
    public void onReceive(Object message) throws Exception {

    }

}
