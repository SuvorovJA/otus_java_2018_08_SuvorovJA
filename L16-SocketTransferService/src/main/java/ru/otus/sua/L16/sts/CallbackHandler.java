package ru.otus.sua.L16.sts;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.sts.abstractions.Pollable;
import ru.otus.sua.L16.sts.entities.Message;

import java.io.Closeable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
public class CallbackHandler implements Closeable {

    private ScheduledExecutorService executorService;
    private int initDelay;
    private int period;
    private Consumer<Message> callback;
    private Pollable pollableObject;
    private boolean interrupt;

    public CallbackHandler(int initDelay, int period, Consumer<Message> consumer, Pollable pollable) {
        this.pollableObject = pollable;
        this.callback = consumer;
        this.initDelay = initDelay;
        this.period = period;
        this.interrupt = false;
    }

    private void setInterrupt() {
        interrupt = true;
    }

    public void start() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::poller, initDelay, period, TimeUnit.MILLISECONDS);
        log.info("Started new worker for polling \'{}\' in period \'{}\' and callback to \'{}\'",
                pollableObject, period, callback);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void poller() {
        while (!interrupt) {
            Message message = pollableObject.poll();
            if (message != null) {
                log.info("traffic: \'{}\'", message);
                callback.accept(message);
            }
        }
    }

    @Override
    public void close() {
        setInterrupt();
        executorService.shutdownNow();
    }
}
