package ru.otus.sua.L10.executor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

class ParsedFieldSimple {

    private String name;
    private int modifiers;
    private Class<?> type;
    private boolean isWrapper;

    ParsedFieldSimple(Field field) {
        type = field.getType();
        name = field.getName();
        modifiers = field.getModifiers();
        isWrapper = isNumber() || isChar();
    }

    boolean isString() {
        return (type.equals(String.class));
    }

    boolean isPrimitive() {
        return (type.isPrimitive());
    }

    boolean isChar() {
        return (Character.class.equals(type) || type.equals(char.class));
    }

    boolean isNumber() {
        return (type.isAssignableFrom(Number.class));
    }

    boolean isInt() {
        return (type.equals(int.class));
    }

    boolean isArray() {
        return (type.isArray());
    }

    boolean isCollection() {
        return (type.isAssignableFrom(Collection.class));
    }

    String getName() {
        return name;
    }

    boolean isWrapperField() {
        return isWrapper && name.equals("value");
    }

    boolean isSkippable() {
        boolean isTransient = Modifier.isTransient(modifiers);
        boolean isStatic = Modifier.isStatic(modifiers);
        return isTransient || isStatic;
    }

}
