package ru.otus.sua.L07.repl.staff;

public interface AtmCommands extends ReplCommands {

    String put(String args) throws IllegalArgumentException;

    String get(long nonNegativeSum);

    String available();

    String balance();

    String exit();

    String setValute(String valuteCode);

}
