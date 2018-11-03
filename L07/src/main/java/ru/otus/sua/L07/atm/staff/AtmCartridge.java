package ru.otus.sua.L07.atm.staff;

import ru.otus.sua.L07.atm.Cartridge;

public interface AtmCartridge {

    void charging(Multivalute valute, Nominal nominal, long amount);

    void discharging(Multivalute valute, Nominal nominal, long amount) throws ImpossibleDischarging;

    void chargingByCartridge(Cartridge cartridge);

    void dischargingByCartridge(Cartridge cartridge) throws ImpossibleDischarging;

    Nominal getMinimalAvailableNominal();

}
