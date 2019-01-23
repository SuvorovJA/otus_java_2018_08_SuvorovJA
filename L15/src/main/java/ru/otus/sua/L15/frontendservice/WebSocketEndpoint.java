package ru.otus.sua.L15.frontendservice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L15.messagesystem.Destination;
import ru.otus.sua.L15.messagesystem.MSConsumer;
import ru.otus.sua.L15.messagesystem.Message;
import ru.otus.sua.L15.messagesystem.MessageSystem;
import ru.otus.sua.L15.starting.CdiAwareConfigurator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Data
@ServerEndpoint(value = "/ws", configurator = CdiAwareConfigurator.class)
@Dependent
public class WebSocketEndpoint implements FrontendService, MSConsumer {

    private static final String MESSAGE_SYSTEM_QUEUENAME = "Frontend_With_Db_Conversation";
    private static final String MESSAGE_SYSTEM_SENDERNAME = "FrontendService";
    private static final Queue<Session> clientsSessions = new ConcurrentLinkedQueue<>();
    private static final Map<UUID, Session> messagesJournal = new ConcurrentHashMap<>();
    private final Queue<Session> closedSessions = new ConcurrentLinkedQueue<>();

    @Inject
    private MessageSystem messageSystem;

    @OnMessage
    public void onMessage(Session session, String requestedString) {
        log.info("Used MessageSystem: \'{}\'", messageSystem);
        log.info("Received string \'{}\' from session id: {}. Route to message system.", requestedString, session.getId());
        Message message = new Message(
                messageSystem.getDestination(MESSAGE_SYSTEM_QUEUENAME),
                requestedString,
                MESSAGE_SYSTEM_SENDERNAME + "/WSSID" + session.getId(),
                UUID.randomUUID(),
                this);
        messagesJournal.put(message.getDialogId(), session);
        messageSystem.putMessage(message);
    }

    @OnOpen
    public void open(Session session) throws IOException {
        log.info("New WS-session opened id: {}", session.getId());
        clientsSessions.add(session);
    }

    @OnError
    public void error(Session session, Throwable t) {
        clientsSessions.remove(session);
        log.error("Error on WS-session id: {}", session.getId());
    }

    @OnClose
    public void closedConnection(Session session) {
        clientsSessions.remove(session);
        log.info("WS-session closed id: {}", session.getId());
    }

    private synchronized void sendAll(String msg) {
        try {
            if (clientsSessions.size() > 0) {
                for (Session session : clientsSessions) {
                    sendSingle(msg, session);
                }
                removeClosed();
                log.info("Sending to " + clientsSessions.size() + " clients, " + msg);
            } else {
                log.info("No clients, no send.");
            }
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    private synchronized void sendSingle(String msg, Session session) throws IOException {
        if (session.isOpen()) {
            session.getBasicRemote().sendText(msg);
        } else {
            log.error("Attempt to send to Closed WS session id: " + session.getId());
            closedSessions.add(session);
        }
    }

    private synchronized void removeClosed() {
        clientsSessions.removeAll(closedSessions);
        for (Session s : closedSessions) {
            if (messagesJournal.containsValue(s)) {
                while (messagesJournal.values().remove(s)) ;
            }
        }
        closedSessions.clear();

    }

    @Override
    public void exec(Message message) {
        Session session = messagesJournal.get(message.getDialogId());
        if (session == null) {
            log.info("Reseived response message for unexistent session. Lost message.");
        } else {
            log.info("Reseived response message \'{}\' for session \'{}\'", message.getMessage(), session.getId());
            try {
                sendSingle(message.getMessage(), session);
                messagesJournal.remove(message.getDialogId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void start(){
        Destination destination = messageSystem.getDestination(MESSAGE_SYSTEM_QUEUENAME);
        messageSystem.subscribe(destination,this);
    }

    @PreDestroy
    public void stop() {
        clientsSessions.clear();
        closedSessions.clear();
        messagesJournal.clear();
    }
}

