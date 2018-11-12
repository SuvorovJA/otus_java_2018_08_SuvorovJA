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

    <T extends Number> T getTypedValue(Class<T> t) {
        return (T) value;
    }

    boolean isString() {
        return (type.equals(String.class));
    }

    boolean isPrimitive() {
        return (type.isPrimitive());
    }

    boolean isInt() {
        return (Integer.class.equals(type) || int.class.equals(type));
    }

    boolean isLong() {
        return (Long.class.equals(type) || long.class.equals(type));
    }

    boolean isShort() {
        return (Short.class.equals(type) || short.class.equals(type));
    }

    boolean isByte() {
        return (Byte.class.equals(type) || byte.class.equals(type));
    }

    boolean isFloat() {
        return (Float.class.equals(type) || float.class.equals(type));
    }

    boolean isDouble() {
        return (Double.class.equals(type) || double.class.equals(type));
    }

    boolean isBool() {
        return (Boolean.class.equals(type) || boolean.class.equals(type));
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

    Class<?> getType() {
        return type;
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
