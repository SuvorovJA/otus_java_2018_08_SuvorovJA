package ru.otus.sua.L07.repl;

import ru.otus.sua.L07.repl.staff.ReplCommands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Repl implements Runnable {

    private ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");

    private Map<String, ReplCommands> commandsMap = new HashMap<>();

    public void addCommands(String name, ReplCommands commands) {
        this.commandsMap.put(name, commands);
    }

    private String version() {
        return (nashorn.getFactory().getEngineName() + " " +
                nashorn.getFactory().getEngineVersion() + "\n" +
                nashorn.getFactory().getLanguageName() + " " +
                nashorn.getFactory().getLanguageVersion() + "\n\n" +
                (commandsMap.containsKey("help") ? commandsMap.get("help").help() : "\n")
        );
    }

    @Override
    public void run() {

        ReplConfigApply();

        System.out.println(version());

        try (Scanner scanner = new Scanner(System.in)) {
            Supplier<String> input = () -> {
                System.out.print("> ");
                return scanner.nextLine();
            };

            String quit = "quit";
            Function<String, String> expressionHandler = expression -> {
                if (quit.equals(expression)) return quit;
                try {
                    System.out.println(nashorn.eval(expression));
                    return "OK";
                } catch (ScriptException e) {
                    System.out.println(e.getMessage());
                    return "ERR";
                }
            };
            Predicate<String> quitCommand = (command) -> quit.equalsIgnoreCase(command.trim());
            Stream.generate(input).map(expressionHandler).noneMatch(quitCommand);
        }
    }

    private void ReplConfigApply() {
        for (String key : commandsMap.keySet()) {
            nashorn.put(key, key.contains("help") ? commandsMap.get(key).help() : commandsMap.get(key));
        }
    }

}


