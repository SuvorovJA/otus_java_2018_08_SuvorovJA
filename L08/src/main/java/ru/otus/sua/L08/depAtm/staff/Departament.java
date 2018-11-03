package ru.otus.sua.L08.depAtm.staff;

import ru.otus.sua.L07.atm.staff.AtmMachine;
import ru.otus.sua.L07.atm.staff.Nominal;

import java.util.EnumMap;
import java.util.List;

public interface Departament {

    String getAtmNames();

    void addAtm(String name, AtmMachine machine);

    long balanceTotal();

    List<EnumMap<Nominal,Long>> availableTotal();

}
