package ru.otus.sua.L10.executor;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class ReflectionHelper {

    private static Map<Class<?>, List<Field>> classFields = new ConcurrentHashMap<>();
    private static Map<String, String> classFieldNamesExceptId = new ConcurrentHashMap<>();


    public static List<Field> getFields(Class clazz) {
        if (!classFields.containsKey(clazz)) return getAllClassFields(clazz);
        return classFields.get(clazz);
    }

    public static String getFieldNames(Class clazz, Function<Field, String> method) {
        if (!classFieldNamesExceptId.containsKey(getKeyString(clazz,method)))
            return getAllClassFieldNamesExceptId(getFields(clazz),method,clazz);
        return classFieldNamesExceptId.get(getKeyString(clazz,method));
    }

    private static List<Field> getAllClassFields(Class clazz) {
        Class c = clazz;
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList());
        while (c.getSuperclass() != null) {
            List<Field> superFields = Arrays.stream(c.getSuperclass().getDeclaredFields()).collect(Collectors.toList());
            fields.addAll(superFields);
            c = c.getSuperclass();
        }
        classFields.put(clazz, fields);
        return fields;
    }


    private static String getAllClassFieldNamesExceptId(List<Field> fields, Function<Field, String> method, Class clazz) {
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) continue;
            sb.append(field.getName());
            appendExternalData(method, sb, field);
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        classFieldNamesExceptId.put(getKeyString(clazz,method),sb.toString());
        return sb.toString();
    }

    private static void appendExternalData(Function<Field, String> method, StringBuilder sb, Field field) {
        if (method != null) {
            sb.append(method.apply(field));
        }
    }

    private static String getKeyString(Class clazz, Function<Field, String> method){
        return (clazz.getSimpleName() + Objects.toString(method,"null"));
    }
}
