package ru.otus.sua.L04.framework;

import ru.otus.sua.L04.framework.annotations.After;
import ru.otus.sua.L04.framework.annotations.Before;
import ru.otus.sua.L04.framework.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tester {

    public static void doClass(Class<?> testableClass) {
        logger(testableClass.getName());

        logger(getAnnotatedMethods(testableClass, Before.class).stream().map(m -> m.getName()).collect(Collectors.joining(", ")));
        executeMethods(testableClass, getAnnotatedMethods(testableClass, Before.class));

        logger(getAnnotatedMethods(testableClass, Test.class).stream().map(m -> m.getName()).collect(Collectors.joining(", ")));
        executeMethods(testableClass, getAnnotatedMethods(testableClass, Test.class));

        logger(getAnnotatedMethods(testableClass, After.class).stream().map(m -> m.getName()).collect(Collectors.joining(", ")));
        executeMethods(testableClass, getAnnotatedMethods(testableClass, After.class));
    }

    public static void doPackage(String testablePackage) {
        logger(testablePackage);
    }


    private static void executeMethods(Class<?> testableClass, List<Method> loms) {
        // TODO move object instantiation to uplevel, for minimize instantiations
        Object o;
        try {
            o = getInstance(testableClass);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            logger("Skipping. Object Instantiation error. " + e.getMessage());
            return;
        }
        for (Method m : loms) {
            executeMetod(o, m);
        }
    }

    private static void executeMetod(Object o, Method m) {
        logger(m.getName() + ", params:" + Arrays.toString(m.getParameters()));
        // TODO make method invoking with params
        if (m.getParameters().length != 0){
            logger("Skipping. Method have parameters.");
            return;
        }
        try {
            m.invoke(o);
            logger("SUCCESS.");
        } catch (IllegalAccessException e) {
            logger("Skipping. Method invocation error. " + e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof TestException){
                logger("FAIL.");
            } else {
                logger("Skipping. Method invocation error. " + e.getMessage());
            }
        } catch (Exception e) {
            logger("FAIL..");
        }
    }

    private static <T> T getInstance(Class<T> testableClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        logger(Arrays.toString(testableClass.getConstructors()));
        // TODO add selector of constructor (for not existed non-parameter constructor)
        // TODO add operation for parametrized constructors
        return testableClass.getConstructor().newInstance();
    }

    /**
     * getAnnotatedMethods not look to privates
     *
     * @param c - Examined Class
     * @param a - Look for this Annotation
     * @return
     */
    private static List<Method> getAnnotatedMethods(Class c, Class a) {
        Method[] methods = c.getMethods();
        return Arrays.stream(methods).filter(m -> m.isAnnotationPresent(a)).collect(Collectors.toList());

    }

    private static void logger(String s) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        System.out.println("   "
                /*+ stackTraceElements[2].getClassName() + "."*/
                + stackTraceElements[2].getMethodName() + "()"
                + ": " + s);
    }
}


