package ru.otus.sua.L07.atm;

import lombok.NoArgsConstructor;
import ru.otus.sua.L07.atm.staff.*;

import java.util.EnumMap;

@NoArgsConstructor
public class Cartridge implements AtmCartridge, AtmAvailable {

    private EnumMap<Nominal, Long> capacity = new EnumMap<>(Nominal.class);

    @Override
    public EnumMap<Nominal, Long> available(Multivalute valute) {
        return capacity.clone();
    }


    @Override
    public void charging(Multivalute valute, Nominal nominal, long amount) {
        long oldAmount = 0;
        if (capacity.containsKey(nominal)) oldAmount = capacity.get(nominal);
        capacity.put(nominal, oldAmount + amount);
    }

    @Override
    public void discharging(Multivalute valute, Nominal nominal, long amount) throws ImpossibleDischarging {
        long oldAmount = 0;
        if (capacity.containsKey(nominal)) oldAmount = capacity.get(nominal);
        if (oldAmount - amount < 0) throw new ImpossibleDischarging();
        capacity.put(nominal, capacity.get(nominal) - amount);
    }
}
