package ru.otus.sua.L16.sts;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.sts.abstractions.Msg;
import ru.otus.sua.L16.sts.abstractions.Pollable;
import ru.otus.sua.L16.sts.abstractions.SocketTransferService;
import ru.otus.sua.L16.sts.abstractions.SocketWorker;

import java.io.IOException;

@Slf4j
public class SocketTransferServiceServer implements SocketTransferService {
    private SocketListener socketListener;

    public SocketTransferServiceServer(int myPort, String myName) {
        socketListener = new SocketListener(myPort, myName);
        socketListener.startBackground();
        log.info("{}: started in server mode", myName);
    }

    @Override
    public void send(Msg msg) {
        for (SocketWorker worker : socketListener.getWorkers()) {
            worker.send(msg);
        }
    }

    @Override
    public <T extends Msg> T poll() {
        for (SocketWorker worker : socketListener.getWorkers()) {
            Msg msg = worker.poll();
            if (msg != null) return (T) msg;
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        return (socketListener != null);
    }

    @Override
    public void close() {
        for (SocketWorker worker : socketListener.getWorkers()) {
            try {
                worker.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socketListener.close();
    }

}
