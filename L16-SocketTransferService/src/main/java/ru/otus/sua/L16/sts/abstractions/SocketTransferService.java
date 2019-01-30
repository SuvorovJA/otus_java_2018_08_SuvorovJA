package ru.otus.sua.L16.sts.abstractions;

import java.io.Closeable;

public interface SocketTransferService extends Closeable {

    void send(Msg msg);

    Msg poll();

    boolean isConnected();

}
