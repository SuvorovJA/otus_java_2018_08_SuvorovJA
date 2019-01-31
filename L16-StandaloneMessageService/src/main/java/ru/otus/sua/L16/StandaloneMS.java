package ru.otus.sua.L16;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.messagesystem.Destination;
import ru.otus.sua.L16.messagesystem.MessageSystem;
import ru.otus.sua.L16.messagesystem.MessageSystemImpl;
import ru.otus.sua.L16.runner.ProcessRunnerImpl;
import ru.otus.sua.L16.sts.CallbackHandler;
import ru.otus.sua.L16.sts.SocketTransferServiceServer;
import ru.otus.sua.L16.sts.abstractions.SocketTransferService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Data
@Slf4j
public class StandaloneMS {
    private static final String DB_JAR = "/home/lsua/DEV/OTUS/java-dev/project/L16-StandaloneDbService/target/StandaloneDbService-2018-08.jar";
    private static final String FRONT_WAR = "/home/lsua/DEV/OTUS/java-dev/project/L16/target/L16.war";
    private static final String TOMCAT_DEPLOYMENT = "/home/lsua/bin/tomcat-9.0.12/webapps/";
    private static final String TOMCAT_STARTER = "/home/lsua/bin/tomcat-9.0.12/bin/startup.sh";
    private static final int CLIENT_START_DELAY_SEC = 5;
    private static final ScheduledExecutorService executorService1 = Executors.newSingleThreadScheduledExecutor();
    private static final ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
    //
    private static final String MESSAGE_SYSTEM_QUEUENAME = "Frontend_With_Db_Conversation";
    private static final int initDelay = 900;
    private static final int pollDelay = 900;
    private MessageSystem messageSystem;
    private SocketTransferService stsConsumer;
    private SocketTransferService stsSupplier;
    private CallbackHandler handlerConsumer;
    private CallbackHandler handlerSupplier;


    public static void main(String[] args) throws IOException {
        StandaloneMS context = new StandaloneMS();
        context.start();
        copyFile(FRONT_WAR, TOMCAT_DEPLOYMENT + "ROOT.war");
        log.info("Success copy frontend war-file");
        startClient(executorService1, "java -jar " + DB_JAR);
        log.info("Success run backend process");
        startClient(executorService2,TOMCAT_STARTER);
        log.info("Success run tomcat process");

        // wait-hangup due to context executors threads
    }

    private static void startClient(ScheduledExecutorService executorService, String command) {
        executorService.schedule(() -> {
            try {
                new ProcessRunnerImpl().start(command);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }, CLIENT_START_DELAY_SEC, TimeUnit.SECONDS);
    }

    private static void copyFile(String from, String to) throws IOException {
        Path src = Paths.get(from);
        Path dst = Paths.get(to);
        Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
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
