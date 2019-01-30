package ru.otus.sua.L16.sts;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.sts.abstractions.Blocks;
import ru.otus.sua.L16.sts.abstractions.SocketWorker;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SocketListener implements Closeable {

    private final int MYPORT;
    private final String MYNAME;
    private final ExecutorService executor;
    private final List<SocketWorker> workers;
    private ServerSocket serverSocketForClosing;

    SocketListener(int myPort, String myName) {
        this.MYPORT = myPort;
        this.MYNAME = myName;
        executor = Executors.newSingleThreadExecutor();
        workers = new CopyOnWriteArrayList<>();
    }

    List<SocketWorker> getWorkers() {
        return workers;
    }

    void startBackground() {
        executor.submit(this::start);
    }

    @Blocks
    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(MYPORT)) {
            this.serverSocketForClosing = serverSocket;
            log.info("Socket listener for \'{}\' started on port: {}", MYNAME, serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                Socket socket = serverSocket.accept(); //blocks
                SocketWorker worker = new SocketWorkerImpl(socket);
                workers.add(worker);
                log.info("{}: New socket established: \'{}\'", MYNAME, worker);
            }
        } catch (IOException e) {
            log.info("Trouble on bind socket listener: \'{}\':\'{}\' caused \'{}\'", MYNAME, MYPORT, e.getMessage());
        }
    }

    @Override
    public void close() {
        executor.shutdownNow();
        for (SocketWorker worker : workers) {
            try {
                worker.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            serverSocketForClosing.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("SocketListener was shutdown");
    }
}