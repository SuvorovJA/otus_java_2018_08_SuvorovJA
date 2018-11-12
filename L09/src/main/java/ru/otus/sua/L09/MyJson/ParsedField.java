package ru.otus.sua.L09.MyJson;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ParsedField {

    private String name;
    private Object value;
    private int modifiers;
    private Class<?> type;
    private boolean isWrapper;

    ParsedField(Field field, Object subject) {
        type = field.getType();
        name = field.getName();
        try {
            value = field.get(subject);
        } catch (IllegalAccessException e) {
            value = e.getMessage();
        }
        modifiers = field.getModifiers();
        isWrapper = subject instanceof Number || subject instanceof Character;
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
        return (value instanceof Number);
    }

    boolean isArray() {
        return (type.isArray());
    }

    boolean isCollection() {
        return (value instanceof Collection);
    }

    String getName() {
        return name;
    }

    Object getValue() {
        return value;
    }

    boolean isWrapperField() {
        return isWrapper && name.equals("value");
    }

    Object[] getValueAsArray() {
        if (!this.isArray()) return null;
        Class ofArray = value.getClass().getComponentType();
        if (ofArray.isPrimitive()) {
            List ar = new ArrayList();
            int length = Array.getLength(value);
            for (int i = 0; i < length; i++) {
                ar.add(Array.get(value, i));
            }
            return ar.toArray();
        } else {
            return (Object[]) value;
        }
    }

    boolean isSkippable() {
        boolean isTransient = Modifier.isTransient(modifiers);
        boolean isStatic = Modifier.isStatic(modifiers);
        return isTransient || isStatic;
    }

}
