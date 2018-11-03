package ru.otus.sua.L08.depAtm;

import ru.otus.sua.L07.atm.staff.AtmMachine;
import ru.otus.sua.L07.atm.staff.Multivalute;
import ru.otus.sua.L07.atm.staff.Nominal;
import ru.otus.sua.L08.atm.staff.Event;
import ru.otus.sua.L08.atm.staff.Observer;
import ru.otus.sua.L08.depAtm.staff.Departament;
import ru.otus.sua.L08.depAtm.staff.Subject;

import java.util.*;

public class AtmDepartament implements Departament, Subject {

    private Map<String, AtmMachine> machineMap = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();

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
    public List<EnumMap<Nominal, Long>> availableTotal() {
        List<EnumMap<Nominal, Long>> cartridges = new ArrayList<>();
        for (AtmMachine machine : machineMap.values()) {
            cartridges.add(machine.available(Multivalute.RUR));
        }
        return cartridges;
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(Event event) {
        observers.forEach(observer -> observer.notify(event));
    }
}
