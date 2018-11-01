package ru.otus.sua.L07.atm;

import lombok.NoArgsConstructor;
import ru.otus.sua.L07.atm.staff.AtmCartridge;
import ru.otus.sua.L07.atm.staff.ImpossibleDischarging;
import ru.otus.sua.L07.atm.staff.Multivalute;
import ru.otus.sua.L07.atm.staff.Nominal;

import java.util.EnumMap;

@NoArgsConstructor
public class Cartridge implements AtmCartridge {
    @Override
    public EnumMap<Nominal, Long> available(Multivalute valute) {
        return null;
    }

    @Override
    public void charging(Multivalute valute, Nominal nominal, long amount) {

    }

    @Override
    public void discharging(Multivalute valute, Nominal nominal, long amount) throws ImpossibleDischarging {

    }
}
