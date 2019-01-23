package ru.otus.sua.L15.messagesystem;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L15.starting.Startup;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;


@Slf4j
@ApplicationScoped
public class MessageSystemImpl implements MessageSystem, Startup {

    private final Map<String, Destination> destinationsByName = new ConcurrentHashMap<>();
    private final Map<Destination, Thread> workers = new ConcurrentHashMap<>();
    private final Map<Destination, MSConsumer> consumers = new ConcurrentHashMap<>();
    private final Map<Destination, MSSupplier> suppliers = new ConcurrentHashMap<>();
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
            log.info("Map of queues length: \'{}\'", destinationQueues.size());
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
        Destination destination = addDestination(message.getDestination());
        if (!destinationQueues.get(destination).offer(message))
            log.info("Occured lost of message in \'{}\', message: \'{}\', from \'{}\'",
                    destination.getName(),
                    message.getMessage(),
                    message.getSenderName());
    }

    @Override
    public void subscribe(Destination destination, MSConsumer consumer) {
        log.info("Subscide consumer \'{}\' for destination \'{}\'", consumer.getClass(), destination.getName());
        consumers.put(destination, consumer);
    }

    @Override
    public void subscribe(Destination destination, MSSupplier supplier) {
        log.info("Subscide supplier \'{}\' for destination \'{}\'", supplier.getClass(), destination.getName());
        suppliers.put(destination, supplier);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startInThread(Destination destination) {
        Thread thread = new Thread(() -> {
            log.info("Worker \'{}\' started", Thread.currentThread().getName());
            BlockingQueue<Message> queue = destinationQueues.get(destination);
            try {
                while (true) {
                    Message message = queue.take();
                    log.info("Traffic occured from \'{}\': \'{}\'", message.getSenderName(), message.getMessage());
                    if (message.getSender() instanceof MSSupplier) {
                        log.info("Route message for dialog: \'{}\' to consumer.", message.getDialogId());
                        consumers.get(destination).exec(message);
                    }
                    if (message.getSender() instanceof MSConsumer) {
                        log.info("Route message for dialog: \'{}\' to supplier.", message.getDialogId());
                        suppliers.get(destination).exec(message);
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
    public void stop() {
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
