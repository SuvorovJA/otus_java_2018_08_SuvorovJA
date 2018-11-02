package ru.otus.sua.L07.repl;

import ru.otus.sua.L07.repl.staff.ReplCommands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Repl implements  Runnable {

    private ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");
    private ReplCommands commands;

    public Repl(ReplCommands commands) {
        this.commands = commands;
        nashorn.put("atm", commands);
        nashorn.put("help", commands.help());
    }

    private String version() {
        return (nashorn.getFactory().getEngineName() + " " +
                nashorn.getFactory().getEngineVersion() + "\n" +
                nashorn.getFactory().getLanguageName() + " " +
                nashorn.getFactory().getLanguageVersion()+ "\n\n" +
                commands.help()
        );
    }

    @Override
    public void run() {

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

}


