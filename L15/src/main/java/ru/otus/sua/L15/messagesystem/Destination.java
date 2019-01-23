package ru.otus.sua.L15.messagesystem;

import lombok.Data;

@Data
public final class Destination {
    private static int nextId = 0;
    private final String name;
    private final int id;

    Destination(String name) {
        id = getNextId();
        if (name.isEmpty()) this.name = "default";
        else this.name = name;
    }

    private static synchronized int getNextId() {
        nextId++;
        return nextId;
    }

}
