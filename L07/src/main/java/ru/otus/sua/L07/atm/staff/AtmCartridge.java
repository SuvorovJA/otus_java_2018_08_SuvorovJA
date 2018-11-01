package ru.otus.sua.L07.atm.staff;

import java.util.EnumMap;

public interface AtmCartridge {

    EnumMap<Nominal, Long> available(Multivalute valute);

    void charging(Multivalute valute, Nominal nominal, long amount);

    void discharging(Multivalute valute, Nominal nominal, long amount) throws ImpossibleDischarging;

}
