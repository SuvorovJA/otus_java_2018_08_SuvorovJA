package ru.otus.sua.L07.repl;

import ru.otus.sua.L07.atm.staff.AtmMachine;
import ru.otus.sua.L07.atm.staff.Multivalute;

public class Repl implements ReplCommands, ReplProduce {


    @Override
    public String help() {
        return null;
    }

    @Override
    public String put(String... args) {
        return null;
    }

    @Override
    public String get(long nonNegativeSum) {
        return null;
    }

    @Override
    public String available() {
        return null;
    }

    @Override
    public void exit() {

    }

    @Override
    public String setValute(Multivalute valute) {
        return null;
    }

    @Override
    public void initiate(AtmMachine machine) {

    }
}
