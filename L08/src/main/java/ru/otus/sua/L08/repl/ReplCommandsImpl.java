package ru.otus.sua.L08.repl;


import ru.otus.sua.L07.repl.staff.ReplCommands;


public class ReplCommandsImpl implements ReplCommands {

    @Override
    public String help() {
        return "ATM-DEPARTMENT SIMULATOR INTERFACE\n" +
                "Commands:\n" +
                " help  - this help\n" +
                " dep.balance() - sum of money in ATMs\n" +
                " dep.available() - list of cartridges contents on ATMs\n" +
                " dep.list()  - list ATMs\n" +
                " atmN.help() - help for N\'th ATM\n" +
                " quit | dep.exit() - for exit;\n";

    }

}
