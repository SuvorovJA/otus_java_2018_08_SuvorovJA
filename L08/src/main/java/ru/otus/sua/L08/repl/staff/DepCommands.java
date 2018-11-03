package ru.otus.sua.L08.repl.staff;

import ru.otus.sua.L07.repl.staff.ReplCommands;

public interface DepCommands extends ReplCommands {

    String list();

    String exit();

    String available();

    String balance();

}
