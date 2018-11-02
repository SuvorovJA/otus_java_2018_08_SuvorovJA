package ru.otus.sua.L07.atm.staff;

public interface AtmCartridge {

    void charging(Multivalute valute, Nominal nominal, long amount);

    void discharging(Multivalute valute, Nominal nominal, long amount) throws ImpossibleDischarging;

}
