package ru.otus.sua.L15.messagesystem;

public interface MessageSystem {
    Destination getDestination(String name);

    void removeDestination(Destination destination);

    void putMessage(Message message);

    void subscribe(Destination destination, MSConsumer consumer);

    void subscribe(Destination destination, MSSupplier supplier);
}
