package ru.otus.sua.L04.framework;

public class Tester {

    public static void doClass(Class<?> testableClass) {
        logger(testableClass.getName());
    }

    public static void doPackage(String testablePackage) {
        logger(testablePackage);
    }

    private static void logger(String s) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.out.println("   "
                + stackTraceElements[2].getClassName() + "."
                + stackTraceElements[2].getMethodName() + "()"
                + ": " + s);
    }
}


