package ru.otus.sua.L07.repl;

import lombok.AllArgsConstructor;
import ru.otus.sua.L07.atm.staff.AtmMachine;
import ru.otus.sua.L07.atm.staff.Nominal;
import ru.otus.sua.L07.repl.staff.ReplCommands;

import java.util.EnumMap;

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
    public String put(String args) {

        EnumMap<Nominal, Long> pairs;
        try {
            pairs = parse(args);
        } catch (IllegalArgumentException e) {
            return "ERR: " + e.getMessage();
        }
        return "ADDED: " + pairs.toString();
    }

    private EnumMap<Nominal, Long> parse(String args) throws IllegalArgumentException {
        if (args == null | args.equals("")) throw new IllegalArgumentException("no param");
        EnumMap<Nominal, Long> pairs = new EnumMap<Nominal, Long>(Nominal.class);
        String[] byCommaParts = args.split(",");
        if (byCommaParts.length == 0) throw new IllegalArgumentException("no pairs");
        for (String pairCandidate : byCommaParts) {
            String[] bySlashParts = pairCandidate.split("/");
            if (bySlashParts.length < 2) throw new IllegalArgumentException("no correct pairs");
            Nominal nominal = Nominal.valueOf(bySlashParts[0]); // built in IllegalArgumentException
            Long quantity;
            try {
                quantity = Long.parseLong(bySlashParts[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("incorrect quantity");
            }
            pairs.put(nominal, quantity);
        }
        return pairs;
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
