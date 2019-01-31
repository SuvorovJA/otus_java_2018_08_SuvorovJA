package ru.otus.sua.L16.messagesystem;

import ru.otus.sua.L16.sts.entities.Message;

import java.io.Closeable;
import java.util.function.Consumer;

public interface MessageSystem extends Closeable {
    Destination getDestination(String name);

    void removeDestination(Destination destination);

    void putMessage(Message message);

    void subscribeAsConsumer(Destination destination, Consumer<Message> callback);

    void subscribeAsSupplier(Destination destination, Consumer<Message> callback);
}
