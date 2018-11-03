package ru.otus.sua.L07;

import ru.otus.sua.L07.atm.Atm;
import ru.otus.sua.L07.atm.Cartridge;
import ru.otus.sua.L07.atm.TrivialWithdrawStrategyImpl;
import ru.otus.sua.L07.atm.staff.ImpossibleDischarging;
import ru.otus.sua.L07.atm.staff.Multivalute;
import ru.otus.sua.L07.atm.staff.Nominal;
import ru.otus.sua.L07.atm.staff.WithdrawStrategy;
import ru.otus.sua.L07.repl.Repl;
import ru.otus.sua.L07.repl.AtmCommandsImpl;
import ru.otus.sua.L07.repl.ReplCommandsImpl;

public class Main {

    public static void main(String[] args) {


        Cartridge cartridge = new Cartridge();
        cartridge.charging(Multivalute.RUR,Nominal.N10,1000);
        cartridge.charging(Multivalute.RUR,Nominal.N10,101);
        cartridge.charging(Multivalute.RUR,Nominal.N100,100);
        cartridge.charging(Multivalute.RUR,Nominal.N1000,100);
        cartridge.charging(Multivalute.RUR,Nominal.N200,100);
//      cartridge.charging(Multivalute.RUR,Nominal.N2000,0); // empty slot
        cartridge.charging(Multivalute.RUR,Nominal.N50,10);
        cartridge.charging(Multivalute.RUR,Nominal.N50,101);
        cartridge.charging(Multivalute.RUR,Nominal.N500,100);
        cartridge.charging(Multivalute.RUR,Nominal.N5000,10);
        try {
            cartridge.discharging(Multivalute.RUR, Nominal.N200, 99);
            cartridge.discharging(Multivalute.RUR, Nominal.N500, 9);
        } catch (ImpossibleDischarging e){
            System.out.println("cant discharge. (should not happen)");
        }
        try {
            cartridge.discharging(Multivalute.RUR, Nominal.N2000, 1);
        } catch (ImpossibleDischarging e) {
            System.out.println("cant discharge. (should happen)");
        }
        System.out.println("Initial Cartridge capacity: " + cartridge.available(Multivalute.RUR).toString());

        WithdrawStrategy strategy = new TrivialWithdrawStrategyImpl();

        System.out.println("---------------------------------------------------------------------");
        Repl repl = new Repl();
        repl.addCommands("atm", new AtmCommandsImpl(new Atm(cartridge,strategy)));
        repl.addCommands("help", new ReplCommandsImpl());
        repl.run();

    }
}
