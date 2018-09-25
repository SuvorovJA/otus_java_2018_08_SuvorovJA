package ru.otus.sua.L04.framework;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import ru.otus.sua.L04.framework.annotations.After;
import ru.otus.sua.L04.framework.annotations.Before;
import ru.otus.sua.L04.framework.annotations.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tester {

    public static void doClass(Class<?> testableClass) {
        logger("Class: " + testableClass.getName());

        List<Class<? extends Annotation>> atypes = List.of(Before.class, Test.class, After.class);
        for (Class<? extends Annotation> a : atypes) {
            if (!getAnnotatedMethods(testableClass, a).isEmpty()) {
                logger("Methods: " + getAnnotatedMethods(testableClass, a).stream().map(Method::getName).collect(Collectors.joining(", ")));
                executeMethods(testableClass, getAnnotatedMethods(testableClass, a));
            } else {
                logger("no @" + a.getSimpleName() + " methods");
            }
        }
    }

    public static void doPackage(String testablePackage) throws IOException {
        logger(testablePackage);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassPath classPath = ClassPath.from(classLoader);
        ImmutableSet<ClassPath.ClassInfo> classInfos = classPath.getTopLevelClasses(testablePackage);

        for (ClassPath.ClassInfo ci : classInfos) {
            doClass(ci.load());
        }
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
        logger(m.getName() + "(" + Arrays.toString(m.getParameters()) + ")");
        // TODO make method invoking with params
        if (m.getParameters().length != 0) {
            logger(">>>SKIP<<< (method have parameters. not realized.)");
            return;
        }
        try {
            m.invoke(o);
            logger(">>>SUCCESS<<<");
        } catch (IllegalAccessException e) {
            logger("Skipping. Method invocation error. " + e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof TestException) {
                logger(">>>FAIL<<<");
            } else {
                logger("Skipping. Method invocation error. " + e.getMessage());
            }
        } catch (Exception e) {
            logger(">>FAIL<<");
        }
    }

    private static <T> T getInstance(Class<T> testableClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        logger("Constructors: " + Arrays.toString(testableClass.getConstructors()));
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


