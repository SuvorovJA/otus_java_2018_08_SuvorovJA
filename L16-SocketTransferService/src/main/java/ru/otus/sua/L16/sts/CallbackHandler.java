package ru.otus.sua.L16.sts;

import ru.otus.sua.L16.sts.abstractions.Msg;
import ru.otus.sua.L16.sts.abstractions.Pollable;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class CallbackHandler implements Closeable {

    private ScheduledExecutorService executorService;
    private int initDelay;
    private int period;
    private Consumer<Msg> callback;
    private Pollable pollableObject;
    private boolean interrupt;

    private void setInterrupt(){
        interrupt=true;
    }

    public CallbackHandler(int initDelay, int period, Consumer<Msg> consumer, Pollable pollable) {
        this.pollableObject = pollable;
        this.callback = consumer;
        this.initDelay = initDelay;
        this.period = period;
        this.interrupt = false;
    }

    public void start() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::poller, initDelay, period, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void poller() {
        while (!interrupt) {
            Msg msg = pollableObject.poll();
            if (msg != null) {
                callback.accept(msg);
            }
        }
    }

    @Override
    public void close() {
        setInterrupt();
        executorService.shutdownNow();
    }
}
