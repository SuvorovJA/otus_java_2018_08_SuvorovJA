package ru.otus.sua.L16.sts;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.sts.abstractions.Msg;
import ru.otus.sua.L16.sts.abstractions.Pollable;
import ru.otus.sua.L16.sts.abstractions.SocketTransferService;
import ru.otus.sua.L16.sts.abstractions.SocketWorker;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class SocketTransferServiceClient implements SocketTransferService, Pollable {

    private static long ATTEMPT_DELAY_MS = 1000;
    private final int THEIRPORT;
    private final String THEIRHOST;
    private final String MYNAME;
    private SocketWorker client;
    private ExecutorService executor;

    public SocketTransferServiceClient(int theirPort, String theirHost, String myName, long attemptsDelay) {
        this(theirPort,theirHost,myName);
        ATTEMPT_DELAY_MS = attemptsDelay;
    }

    public SocketTransferServiceClient(int theirPort, String theirHost, String myName) {
        THEIRPORT = theirPort;
        THEIRHOST = theirHost;
        MYNAME = myName;
        executor = Executors.newSingleThreadExecutor();
        executor.submit(this::channelEstablisher);
        log.info("{}: started in client mode", MYNAME);
    }

    @Override
    public void send(Msg msg) {
        if (client != null) client.send(msg);
    }

    @Override
    public Msg poll() {
        if (client == null) return null;
        return client.poll();
    }

    @Override
    public boolean isConnected() {
        return (client != null);
    }

    @Override
    public void close() throws IOException {
        executor.shutdownNow();
        if (client != null) client.close();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void channelEstablisher() {
        while (true) {
            log.info("{}: Attempt connect to their: \'{}:{}\'", MYNAME, THEIRHOST, THEIRPORT);
            try {
                Socket socket = new Socket(THEIRHOST, THEIRPORT);
                client = new SocketWorkerImpl(socket);
                log.info("{}: Succesfull connected to \'{}:{}\'", MYNAME, THEIRHOST, THEIRPORT);
                return;
            } catch (IOException e) {
                log.warn("{}: Trouble on create client socket to \'{}:{}\' caused \'{}\'",
                        MYNAME, THEIRHOST, THEIRPORT, e.getMessage());
            }

            try {
                Thread.sleep(ATTEMPT_DELAY_MS);
            } catch (InterruptedException e) {
                return;
            }
        }
    }


}
