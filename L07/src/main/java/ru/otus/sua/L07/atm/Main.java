package ru.otus.sua.L07.atm;

import ru.otus.sua.L07.atm.staff.AtmMachine;
import ru.otus.sua.L07.repl.Repl;
import ru.otus.sua.L07.repl.ReplCommandsImpl;

public class Main {

    public static void main(String[] args) {
        AtmMachine machine = new Atm();
        // build Cartridge
        // charge Cartridge
        // build WithdrawStrategy
        // insert Cartridge to ATM
        // insert WithdrawStrategy to ATM

        Repl repl = new Repl(new ReplCommandsImpl(machine));
        repl.run();

    }
}
