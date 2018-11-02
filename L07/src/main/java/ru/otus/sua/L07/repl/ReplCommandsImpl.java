package ru.otus.sua.L07.repl;

import lombok.AllArgsConstructor;
import ru.otus.sua.L07.atm.staff.AtmMachine;
import ru.otus.sua.L07.repl.staff.ReplCommands;

@AllArgsConstructor
public class ReplCommandsImpl implements ReplCommands {

    AtmMachine machine;

    @Override
    public String help() {
        return "ATM SIMULATOR INTERFACE\n" +
                "Commands:\n" +
                " help | atm.help() - this help\n" +
                " quit | atm.exit() - for exit;\n" +
                " atm.put(\"N1000/3,N100/2, ...\") - put to ATM Nominal/Quantity pairs\n" +
                " atm.get(1000) - get sum from ATM\n" +
                " available | atm.available() - show how many Money in ATM\n" +
                " atm.setValute(\"RUR\") - change Valute for operations.";
    }

    @Override
    public String put(String... args) {
        return "NOT IMPLEMENTED";
    }

    @Override
    public String get(long nonNegativeSum) {
        return "NOT IMPLEMENTED";
    }

    @Override
    public String available() {
        return "AVAILABLE: ";
    }

    @Override
    public String exit() {
        System.exit(0);
        return "EXIT";
    }

    @Override
    public String setValute(String valute) {
        return "NOT IMPLEMENTED";
    }
}
