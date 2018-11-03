package ru.otus.sua.L07.repl;

import ru.otus.sua.L07.repl.staff.ReplCommands;

public class ReplCommandsImpl implements ReplCommands {

    @Override
    public String help() {
        return "ATM SIMULATOR INTERFACE\n" +
                "Commands:\n" +
                " help | atm.help() - this help\n" +
                " quit | atm.exit() - for exit;\n" +
                " atm.put(\"N1000/3,N100/2, ...\") - put to ATM Nominal/Quantity pairs\n" +
                " atm.get(1000) - get sum from ATM\n" +
                " atm.available() - show how many Money in ATM\n" +
                " atm.balance() - show total sum of Money in ATM\n" +
                " atm.setValute(\"RUR\") - change Valute for operations.";
    }

}
