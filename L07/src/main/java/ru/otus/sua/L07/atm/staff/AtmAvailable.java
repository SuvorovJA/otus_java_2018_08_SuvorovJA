package ru.otus.sua.L07.atm.staff;

import java.util.EnumMap;

public interface AtmAvailable {
    EnumMap<Nominal, Long> available(Multivalute valute);
}
