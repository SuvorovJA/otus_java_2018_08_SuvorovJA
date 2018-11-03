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
        if (oldAmount - amount < 0) throw new ImpossibleDischarging("Impossible Discharging");
        capacity.put(nominal, oldAmount - amount);
    }

    @Override
    public void chargingByCartridge(Cartridge cartridge) {
        EnumMap<Nominal, Long> external = cartridge.available(Multivalute.RUR);
        for (Nominal nominal : external.keySet()) {
            if (external.get(nominal) == null || (external.get(nominal) <= 0)) continue;
            long oldAmount = 0;
            if (capacity.containsKey(nominal) && capacity.get(nominal) != null) oldAmount = capacity.get(nominal);
            capacity.put(nominal, oldAmount + external.get(nominal));
        }
    }

    @Override
    public void dischargingByCartridge(Cartridge cartridge) throws ImpossibleDischarging {
        EnumMap<Nominal, Long> external = cartridge.available(Multivalute.RUR);
        for (Nominal nominal : external.keySet()) {
            if (external.get(nominal) == null || (external.get(nominal) <= 0)) continue;
            long oldAmount = 0;
            if (capacity.containsKey(nominal) && capacity.get(nominal) != null) oldAmount = capacity.get(nominal);
            if (oldAmount < external.get(nominal))
                throw new ImpossibleDischarging("Impossible Discharging. Old < Sub for nominal " + nominal.toString());
            capacity.put(nominal, oldAmount - external.get(nominal));
        }
    }

    @Override
    public Nominal getMinimalAvailableNominal() {
        Nominal minimalAvailableNominal = null;
        Nominal[] nominals = Nominal.values();
        for (int i = nominals.length - 1; i >= 0; i--) {
            if (capacity.get(nominals[i]) == null || capacity.get(nominals[i]) <= 0) continue;
            minimalAvailableNominal = nominals[i];
            break;
        }
        return minimalAvailableNominal;
    }
}
