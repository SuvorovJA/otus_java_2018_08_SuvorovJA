package ru.otus.sua.L16.sts.abstractions;

import java.io.Closeable;

/**
 * Created by tully.
 */
public interface SocketWorker extends Closeable {
    void send(Msg msg);

    Msg poll();

    @Blocks
    Msg take() throws InterruptedException;

}
