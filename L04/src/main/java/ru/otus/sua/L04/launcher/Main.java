package ru.otus.sua.L04.launcher;

import ru.otus.sua.L04.framework.Tester;
import ru.otus.sua.L04.testable.Testable;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Run with class:");
        Tester.doClass(Testable.class);
        System.out.println("Run with package name:");
        Tester.doPackage("ru.otus.sua.L04.testable");
    }
}
