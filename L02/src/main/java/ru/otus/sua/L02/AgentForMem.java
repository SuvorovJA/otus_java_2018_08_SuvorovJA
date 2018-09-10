package ru.otus.sua.L02;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class AgentForMem {

    private static Instrumentation inst;

    public AgentForMem() {
    }

    public static void premain(String args, Instrumentation instrumentation) {
        inst = instrumentation;
    }

    public static long sizeOf(Object obj) {
        if (inst == null) {
            throw new IllegalStateException("Instrumentation is null");
        } else {
            return inst.getObjectSize(obj);
        }
    }

    public static long getObjSize(Object obj) {
        Map<Object, Object> ihm = new HashMap<>();
        return getObjSize(obj, ihm);
    }

    private static long getObjSize(Object obj, Map<Object, Object> ihm) {
        if (obj == null) {
            return 0L;
        } else {
            long result = 0L;
            if (ihm.containsKey(obj)) {
                return 0L;
            } else {
                ihm.put(obj.hashCode(), obj);
                result = sizeOf(obj);
                int fiedsCount;
                int i;
                if (obj instanceof Object[]) {
                    Object[] objects = ((Object[]) obj);
                    fiedsCount = objects.length;
                    for (i = 0; i < fiedsCount; ++i) {
                        Object o = objects[i];
                        result += getObjSize(o, ihm);
                    }
                } else {
                    Field[] fields = obj.getClass().getDeclaredFields();
                    fiedsCount = fields.length;
                    for (i = 0; i < fiedsCount; ++i) {
                        Field field = fields[i];
                        field.setAccessible(true);
                        Object o;
                        try {
                            o = field.get(obj);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                        if (isComputable(field)) {
                            result += getObjSize(o, ihm);
                        }
                    }
                }
                return result;
            }
        }
    }

    private static boolean isComputable(Field field) {
        int modifiers = field.getModifiers();
        if (isPrimitive(field.getType())) {
            return false;
        } else return !Modifier.isStatic(modifiers);
    }

    private static boolean isPrimitive(Class c) {
        if (c == Boolean.TYPE) {
            return true;
        } else if (c == Character.TYPE) {
            return true;
        } else if (c == Byte.TYPE) {
            return true;
        } else if (c == Short.TYPE) {
            return true;
        } else if (c == Integer.TYPE) {
            return true;
        } else if (c == Long.TYPE) {
            return true;
        } else if (c == Float.TYPE) {
            return true;
        } else if (c == Double.TYPE) {
            return true;
        } else {
            return c == Void.TYPE;
        }
    }

}

