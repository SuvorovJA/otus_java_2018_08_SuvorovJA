package ru.otus.sua.L16.messagesystem;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.sts.entities.Message;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;


@Slf4j
public class MessageSystemImpl implements MessageSystem {

    private final Map<String, Destination> destinationsByName = new ConcurrentHashMap<>();
    private final Map<Destination, Thread> workers = new ConcurrentHashMap<>();
    private final Map<Destination, Consumer<Message>> consumers = new ConcurrentHashMap<>();
    private final Map<Destination, Consumer<Message>> suppliers = new ConcurrentHashMap<>();
    private final Map<Destination, BlockingQueue<Message>> destinationQueues = new ConcurrentHashMap<>();

    @Override
    public Destination getDestination(String name) {
        if (destinationsByName.containsKey(name)) {
            return destinationsByName.get(name);
        } else {
            Destination destination = new Destination(name);
            destinationsByName.put(name, destination);
            return addDestination(destination);
        }
    }

    private Destination addDestination(Destination destination) {
        if (!destinationQueues.containsKey(destination)) {
            destinationQueues.put(destination, new LinkedBlockingQueue<>());
            log.info("Register new destination: \'{}\'. Map of queues length: \'{}\'", destination.getName(), destinationQueues.size());
            startInThread(destination);
        }
        return destination;
    }

    @Override
    public void removeDestination(Destination destination) {
        if (workers.containsKey(destination)) {
            workers.get(destination).interrupt();
            workers.remove(destination);
        }
        destinationsByName.remove(destination.getName());
        destinationQueues.remove(destination);
        suppliers.remove(destination);
        consumers.remove(destination);
    }

    @Override
    public void putMessage(Message message) {
        log.info("Attempt to put message to Destination: \'{}\'", message.getDestination());
        Destination destination = getDestination(message.getDestination());
        if (!destinationQueues.get(destination).offer(message))
            log.info("Occured lost of message in \'{}\', message: \'{}\', from \'{}\'",
                    destination.getName(),
                    message.getPayload(),
                    message.getSender());
    }

    @Override
    public void subscribeAsConsumer(Destination destination, Consumer<Message> jerk) {
        log.info("Subscribe consumer \'{}\' for destination \'{}\'", jerk.getClass(), destination.getName());
        consumers.put(destination, jerk);
    }

    @Override
    public void subscribeAsSupplier(Destination destination, Consumer<Message> jerk) {
        log.info("Subscribe supplier \'{}\' for destination \'{}\'", jerk.getClass(), destination.getName());
        suppliers.put(destination, jerk);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startInThread(Destination destination) {
        Thread thread = new Thread(() -> {
            log.info("New Worker \'{}\' started", Thread.currentThread().getName());
            BlockingQueue<Message> queue = destinationQueues.get(destination);
            try {
                while (true) {
                    Message message = queue.take();
                    log.info("Traffic occured from \'{}\': \'{}\'", message.getSender(), message.getPayload());
                    if (message.isSupplier()) {
                        log.info("Route message for dialog: \'{}\' to consumer.", message.getDialogUid());
                        consumers.get(destination).accept(message);
                    } else {
                        log.info("Route message for dialog: \'{}\' to supplier.", message.getDialogUid());
                        suppliers.get(destination).accept(message);
                    }
                }
            } catch (InterruptedException e) {
                log.info("Worker \'{}\' interrupted", Thread.currentThread().getName());
            }
        });
        thread.setName(destination.getName() + "/" + destination.getId());
        workers.put(destination, thread);
        thread.start();
    }

    @Override
    public void close() {
        destinationsByName.clear();
        for (BlockingQueue q : destinationQueues.values()) {
            q.clear();
        }
        for (Thread t : workers.values()) {
            t.interrupt();
        }
        workers.clear();
        destinationQueues.clear();
        consumers.clear();
        suppliers.clear();
    }

}
