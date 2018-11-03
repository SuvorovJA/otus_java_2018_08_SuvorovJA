package ru.otus.sua.L08.repl;

import lombok.AllArgsConstructor;
import ru.otus.sua.L08.atm.staff.Event;
import ru.otus.sua.L08.depAtm.AtmDepartament;
import ru.otus.sua.L08.repl.staff.DepCommands;

import java.util.AbstractMap;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DepCommandsImpl implements DepCommands {

    AtmDepartament departament;

    @Override
    public String list() {
        return departament.getAtmNames();
    }

    @Override
    public String exit() {
        System.exit(0);
        return "EXIT";
    }

    @Override
    public String available() {
        return departament.availableTotal().stream().map(AbstractMap::toString).collect(Collectors.joining(",\n"));
    }

    @Override
    public String balance() {
        return ((Long)departament.balanceTotal()).toString();
    }

    @Override
    public String notify(String event) {
        if(event.equals("RESET")) {
            departament.notify(Event.RESET);
            return "OK";
        } else {
            return "UNKNOWN EVENT";
        }
    }

    @Override
    public String help() {
        return "this will dept help";
    }


}
