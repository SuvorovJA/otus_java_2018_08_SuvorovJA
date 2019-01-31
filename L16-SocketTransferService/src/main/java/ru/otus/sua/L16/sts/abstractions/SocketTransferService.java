package ru.otus.sua.L16.sts.abstractions;

import java.io.Closeable;

public interface SocketTransferService extends Closeable, Pollable {

    void send(Msg msg);

    boolean isConnected();

}
