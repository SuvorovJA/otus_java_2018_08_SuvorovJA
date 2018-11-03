package ru.otus.sua.L08.repl;

import lombok.AllArgsConstructor;
import ru.otus.sua.L08.depAtm.staff.Departament;
import ru.otus.sua.L08.repl.staff.DepCommands;

import java.util.AbstractMap;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DepCommandsImpl implements DepCommands {

    Departament departament;

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
    public String help() {
        return "this dept help";
    }
}
