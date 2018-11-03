package ru.otus.sua.L08;

import ru.otus.sua.L07.atm.Atm;
import ru.otus.sua.L07.atm.Cartridge;
import ru.otus.sua.L07.atm.TrivialWithdrawStrategyImpl;
import ru.otus.sua.L07.atm.staff.Multivalute;
import ru.otus.sua.L07.atm.staff.Nominal;
import ru.otus.sua.L07.atm.staff.WithdrawStrategy;
import ru.otus.sua.L07.repl.AtmCommandsImpl;
import ru.otus.sua.L07.repl.Repl;
import ru.otus.sua.L08.depAtm.AtmDepartament;
import ru.otus.sua.L08.depAtm.staff.Departament;
import ru.otus.sua.L08.repl.DepCommandsImpl;
import ru.otus.sua.L08.repl.ReplCommandsImpl;

public class Main {

    public static void main(String[] args) {
        Cartridge cartridge1 = new Cartridge();
        cartridge1.charging(Multivalute.RUR, Nominal.N10, 1000);
        System.out.println("Initial Cartridge1 capacity: " + cartridge1.available(Multivalute.RUR).toString());

        Cartridge cartridge2 = new Cartridge();
        cartridge2.charging(Multivalute.RUR, Nominal.N5000, 120);
        System.out.println("Initial Cartridge2 capacity: " + cartridge2.available(Multivalute.RUR).toString());

        WithdrawStrategy strategy = new TrivialWithdrawStrategyImpl();

        Atm atm1 = new Atm(cartridge1, strategy);
        Atm atm2 = new Atm(cartridge2, strategy);

        Departament departament= new AtmDepartament();
        departament.addAtm("atm1",atm1);
        departament.addAtm("atm2",atm2);

        System.out.println("---------------------------------------------------------------------");
        Repl repl = new Repl();
        repl.addCommands("dep", new DepCommandsImpl(departament));
        repl.addCommands("atm1", new AtmCommandsImpl(atm1));
        repl.addCommands("atm2", new AtmCommandsImpl(atm2));
        repl.addCommands("help", new ReplCommandsImpl());
        repl.run();
    }
}
