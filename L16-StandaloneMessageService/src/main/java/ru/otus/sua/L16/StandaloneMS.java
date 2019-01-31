package ru.otus.sua.L16;

import lombok.Data;
import ru.otus.sua.L16.messagesystem.Destination;
import ru.otus.sua.L16.messagesystem.MessageSystem;
import ru.otus.sua.L16.messagesystem.MessageSystemImpl;
import ru.otus.sua.L16.sts.CallbackHandler;
import ru.otus.sua.L16.sts.SocketTransferServiceServer;
import ru.otus.sua.L16.sts.abstractions.SocketTransferService;

@Data
public class StandaloneMS {

    private static final String MESSAGE_SYSTEM_QUEUENAME = "Frontend_With_Db_Conversation";
    private static final int initDelay = 900;
    private static final int pollDelay = 900;
    private MessageSystem messageSystem;
    private SocketTransferService stsConsumer;
    private SocketTransferService stsSupplier;
    private CallbackHandler handlerConsumer;
    private CallbackHandler handlerSupplier;


    public static void main(String[] args) {
        StandaloneMS context = new StandaloneMS();
        context.start();
        // start clients
        // copy L16.war
        // run dbservice
        // wait hangup
    }

    private void start() {

        // start STS
        stsConsumer = new SocketTransferServiceServer(10001, "STS-for-Front-interact");
        stsSupplier = new SocketTransferServiceServer(10002, "STS-for-DB-interact");
        // start MS
        messageSystem = new MessageSystemImpl();
        Destination destination = messageSystem.getDestination(MESSAGE_SYSTEM_QUEUENAME);
        // connect STS with MS
        messageSystem.subscribeAsConsumer(destination, stsConsumer::send);
        messageSystem.subscribeAsSupplier(destination, stsSupplier::send);
        handlerConsumer = new CallbackHandler(initDelay, pollDelay, messageSystem::putMessage, stsConsumer);
        handlerSupplier = new CallbackHandler(initDelay, pollDelay, messageSystem::putMessage, stsSupplier);
        handlerConsumer.start();
        handlerSupplier.start();

    }

}
