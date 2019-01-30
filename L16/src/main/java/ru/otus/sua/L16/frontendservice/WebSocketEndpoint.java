package ru.otus.sua.L16.frontendservice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.starting.CdiAwareConfigurator;
import ru.otus.sua.L16.sts.SocketTransferServiceClient;
import ru.otus.sua.L16.sts.abstractions.Msg;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
@Data
@ServerEndpoint(value = "/ws", configurator = CdiAwareConfigurator.class)
@Dependent
public class WebSocketEndpoint implements FrontendService, MSConsumer {

    private static final String MESSAGE_SYSTEM_QUEUENAME = "Frontend_With_Db_Conversation";
    private static final String MESSAGE_SYSTEM_SENDERNAME = "FrontendService";
    private static final Queue<Session> clientsSessions = new ConcurrentLinkedQueue<>();
    private static final Map<UUID, Session> messagesJournal = new ConcurrentHashMap<>();
    //
    private static final int THEIR_PORT = 10001;
    private static final String THEIR_HOST = "localhost";
    private static final String STS_NAME = "Frontend";
    //
    private final Queue<Session> closedSessions = new ConcurrentLinkedQueue<>();
    private SocketTransferServiceClient socketTransferServiceClient;
    //
    private ScheduledExecutorService executorService;

    @OnMessage
    public void onMessage(Session session, String requestedString) {
        log.info("Used MessageSystem: \'{}\'", socketTransferServiceClient);
        log.info("Received string \'{}\' from session id: {}. Route to message system.", requestedString, session.getId());
        Message message = new Message(
                new Destination(MESSAGE_SYSTEM_QUEUENAME),
                requestedString,
                MESSAGE_SYSTEM_SENDERNAME + "/WSSID" + session.getId(),
                UUID.randomUUID(),
                this);
        messagesJournal.put(message.getDialogId(), session);
        Msg msg = new MessageMsg(message);
        socketTransferServiceClient.send(msg);
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

    @SuppressWarnings("InfiniteLoopStatement")
    private void poller() {
        while (true) {
            Msg msg = socketTransferServiceClient.poll();
            if (msg instanceof MessageMsg) {
                MessageMsg messageMsg = (MessageMsg) msg;
                exec(messageMsg.getMessage());
            }
        }
    }

    @PostConstruct
    public void start() {
        socketTransferServiceClient = new SocketTransferServiceClient(THEIR_PORT, THEIR_HOST, STS_NAME);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::poller, 1111, 111, TimeUnit.MILLISECONDS);
    }

    @PreDestroy
    public void stop() {
        clientsSessions.clear();
        closedSessions.clear();
        messagesJournal.clear();
        executorService.shutdownNow();
    }
}
