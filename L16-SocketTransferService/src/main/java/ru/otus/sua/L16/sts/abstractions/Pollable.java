package ru.otus.sua.L16.sts.abstractions;

public interface Pollable {
    <T extends Msg> T poll();
}
