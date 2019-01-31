package ru.otus.sua.L16.backendservice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.dbservice.DBService;
import ru.otus.sua.L16.dbservice.DBServiceHibernateImpl;
import ru.otus.sua.L16.entity.UserDataSet;
import ru.otus.sua.L16.sts.CallbackHandler;
import ru.otus.sua.L16.sts.SocketTransferServiceClient;
import ru.otus.sua.L16.sts.entities.Message;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Data
public class MessagesystemHandlerForDbService implements BackendService, Closeable {

    private static final String MESSAGE_SYSTEM_QUEUENAME = "Frontend_With_Db_Conversation";
    private static final String MESSAGE_SYSTEM_SENDERNAME = "BackendService";
    //
    private static final int THEIR_PORT = 10002;
    private static final int RECONNECT_DELAY = 5000;
    private static final String THEIR_HOST = "localhost";
    private static final String STS_NAME = "Backend";
    private static final boolean isSupplier = true;
    //
    private SocketTransferServiceClient socketTransferServiceClient;
    private CallbackHandler callbackHandler;
    private DBService dbService;

    private void exec(Message message) {
        log.info("Used MessageSystem: \'{}\' ", socketTransferServiceClient);
        log.info("Received request for search in DB: \'{}\'", message.getPayload());
        try {
            UserDataSet userDataSet = dbService.getByName(message.getPayload(), UserDataSet.class);
            Message responseMessage = message.getResponseMessage(
                    Objects.toString(userDataSet, "NOT FOUND"),
                    MESSAGE_SYSTEM_SENDERNAME,
                    isSupplier);
            log.info("Database search result send back: \'{}\'", responseMessage.getPayload());
            socketTransferServiceClient.send(responseMessage);
        } catch (Exception e) {
            log.error("Error occured on db search. Request lost. Cause: {}", e.getMessage());
        }
    }

    @Override
    public DBService getDbService(){
        return dbService;
    }

    @Override
    public void start() {
        dbService = new DBServiceHibernateImpl();
        socketTransferServiceClient = new SocketTransferServiceClient(
                THEIR_PORT, THEIR_HOST, STS_NAME, RECONNECT_DELAY);
        callbackHandler = new CallbackHandler(
                1000, 500,
                this::exec,
                socketTransferServiceClient);
        callbackHandler.start();
    }

    @Override
    public void close() throws IOException {
        callbackHandler.close();
        socketTransferServiceClient.close();
        try {
            dbService.close();
        } catch (Exception e) {
            throw new IOException("Err on close dbService");
        }
    }
}
