package ru.otus.sua.L08.depAtm;

import ru.otus.sua.L07.atm.staff.AtmMachine;
import ru.otus.sua.L07.atm.staff.Multivalute;
import ru.otus.sua.L07.atm.staff.Nominal;
import ru.otus.sua.L08.depAtm.staff.Departament;

import java.util.*;

public class AtmDepartament implements Departament {

    private Map<String, AtmMachine> machineMap = new HashMap<>();

    @Override
    public String getAtmNames() {
        return String.join(",\n", machineMap.keySet());
        // or
        // return machineMap.keySet().stream().collect(Collectors.joining(",\n"));
    }

    @Override
    public void addAtm(String name, AtmMachine machine) {
        machineMap.put(name, machine);
    }

    @Override
    public long balanceTotal() {
        long sum = 0;
        for (AtmMachine machine : machineMap.values()) {
            sum += machine.balanceTotal(Multivalute.RUR);
        }
        return sum;
    }

    @Override
    public List<EnumMap<Nominal,Long>> availableTotal() {
        List<EnumMap<Nominal,Long>> cartridges = new ArrayList<>();
        for (AtmMachine machine : machineMap.values()) {
            cartridges.add(machine.available(Multivalute.RUR));
        }
        return cartridges;
    }
}
