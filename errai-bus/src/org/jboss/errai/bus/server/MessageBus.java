package org.jboss.errai.bus.server;

import org.jboss.errai.bus.client.CommandMessage;
import org.jboss.errai.bus.client.MessageCallback;


public interface MessageBus {
    public static final String WS_SESSION_ID = "WSSessionID";

    public void sendGlobal(CommandMessage message);
    public void sendGlobal(String subject, CommandMessage message);
    public void sendGlobal(String subject, CommandMessage message, boolean fireListeners);

    public void send(CommandMessage message);
    public void send(String sessionid, String subject, CommandMessage message);
    public void send(String sessionid, String subject, CommandMessage message, boolean fireListeners);

    public void send(String subject, CommandMessage message);
    public void send(String subject, CommandMessage message, boolean fireListeners);

    public Payload nextMessage(Object sessionContext);

    public void subscribe(String subject, MessageCallback receiver);

    public void remoteSubscribe(Object sessionContext, String subject);
    public void remoteUnsubscribe(Object sessionContext, String subject);

    public void addGlobalListener(MessageListener listener);
    public void addSubscribeListener(SubscribeListener listener);
    public void addUnsubscribeListener(UnsubscribeListener listener);
}