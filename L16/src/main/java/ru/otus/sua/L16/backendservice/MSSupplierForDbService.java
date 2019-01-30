package ru.otus.sua.L16.backendservice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.dbservice.CurrentDbService;
import ru.otus.sua.L16.dbservice.DBService;
import ru.otus.sua.L16.entity.UserDataSet;
import ru.otus.sua.L16.messagesystem.Destination;
import ru.otus.sua.L16.messagesystem.MSSupplier;
import ru.otus.sua.L16.messagesystem.Message;
import ru.otus.sua.L16.messagesystem.MessageSystem;
import ru.otus.sua.L16.starting.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@Slf4j
@Data
@ApplicationScoped
public class MSSupplierForDbService implements BackendService, MSSupplier, Startup {

    private static final String MESSAGE_SYSTEM_QUEUENAME = "Frontend_With_Db_Conversation";
    private static final String MESSAGE_SYSTEM_SENDERNAME = "BackendService";

    @Inject
    @CurrentDbService
    private DBService dbService;

    @Inject
    private MessageSystem messageSystem;

    @Override
    public void exec(Message message) {
        log.info("Used MessageSystem: \'{}\' ", messageSystem);
        log.info("Received request for search in DB: \'{}\'", message.getMessage());
        try {
            UserDataSet userDataSet = dbService.getByName(message.getMessage(), UserDataSet.class);
            Message responseMessage = message.getResponseMessage();
            responseMessage.setSenderName(MESSAGE_SYSTEM_SENDERNAME);
            responseMessage.setMessage(Objects.toString(userDataSet, "NOT FOUND"));
            responseMessage.setSender(this);
            log.info("Database search result send back: \'{}\'", responseMessage.getMessage());
            messageSystem.putMessage(responseMessage);
        } catch (Exception e) {
            log.error("Error occured on db search. Request lost. Cause: {}", e.getMessage());
        }

    }

    @Override
    public void start() {
        Destination messageSystemDestination = messageSystem.getDestination(MESSAGE_SYSTEM_QUEUENAME);
        messageSystem.subscribe(messageSystemDestination, this);
    }

}
