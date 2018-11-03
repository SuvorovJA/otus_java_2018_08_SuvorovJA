package ru.otus.sua.L07.repl;

import lombok.AllArgsConstructor;
import ru.otus.sua.L07.atm.staff.*;
import ru.otus.sua.L07.repl.staff.AtmCommands;

import java.util.EnumMap;

@AllArgsConstructor
public class AtmCommandsImpl implements AtmCommands {

    AtmMachine machine;

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

    @Override
    public String put(String args) {
        EnumMap<Nominal, Long> packet;
        try {
            packet = parse(args);
        } catch (IllegalArgumentException e) {
            return "ERR: " + e.getMessage();
        }
        long sum;
        try {
            sum = machine.deposit(Multivalute.RUR, packet);
        } catch (ImpossibleDeposit e) {
            return "ERR: " + e.getMessage();
        }
        return "ADDED: " + packet.toString() + "= " + sum + Multivalute.RUR.toString();
    }

    @Override
    public String get(long nonNegativeSum) {
        try {
            return "WITHDRAWED: " + machine.withdraw(Multivalute.RUR,nonNegativeSum).toString();
        } catch (NegativeSum | ImpossibleWithdraw e) {
            return "ERR: " + e.getMessage();
        }
    }

    @Override
    public String available() {
        return machine.available(Multivalute.RUR).toString();
    }

    @Override
    public String balance() {
        return ((Long) machine.balanceTotal(Multivalute.RUR)).toString();
    }

    @Override
    public String exit() {
        System.exit(0);
        return "EXIT";
    }

    @Override
    public String setValute(String valute) {
        return "NOT IMPLEMENTED IN THAT ATM";
    }

    private EnumMap<Nominal, Long> parse(String args) throws IllegalArgumentException {
        if (args == null || args.equals("")) throw new IllegalArgumentException("no param");
        EnumMap<Nominal, Long> packet = new EnumMap<>(Nominal.class);
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
            packet.put(nominal, quantity);
        }
        return packet;
    }

}
