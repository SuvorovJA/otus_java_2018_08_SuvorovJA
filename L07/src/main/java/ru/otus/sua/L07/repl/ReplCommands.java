package ru.otus.sua.L07.repl;

import ru.otus.sua.L07.atm.staff.Multivalute;

public interface ReplCommands {
    String help();

    String put(String... args);

    String get(long nonNegativeSum);

    String available();

    void exit();

    String setValute(Multivalute valute);

}
