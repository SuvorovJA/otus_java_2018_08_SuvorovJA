package ru.otus.sua.L07.repl.staff;

public interface ReplCommands {

    String help();

    String put(String... args);

    String get(long nonNegativeSum);

    String available();

    String exit();

    String setValute(String valuteCode);

}
