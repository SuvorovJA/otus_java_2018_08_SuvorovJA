package ru.otus.sua.L10.executor;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L10.entity.DataSet;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Slf4j
public class SqlStatementBuilder {

    private static Map<Class<?>, String> insertions = new ConcurrentHashMap<>();
    private static Map<Class<?>, String> creations = new ConcurrentHashMap<>();

    public static String selectionNameById(long id, Class clazz) {
        final String SELECT = "SELECT NAME FROM %s WHERE ID=%s";
        return String.format(SELECT, clazz.getSimpleName().toUpperCase(), id);
    }

    public static <T extends DataSet> String selectionAllById(long id, Class<T> clazz) {
        final String SELECT = "SELECT * FROM %s WHERE ID = %s";
        return String.format(SELECT, clazz.getSimpleName().toUpperCase(), id);
    }

    public static <T extends DataSet> String dataInsertion(T entity) {
        Class<T> tClass = (Class<T>) entity.getClass();
        if (!insertions.containsKey(tClass)) insertions.put(tClass, buildSQLforDataInsertion(entity));
        return fillSQLforDataInsertion(entity, insertions.get(tClass));
    }

    public static String tableCreation(Class clazz) {
        if (!insertions.containsKey(clazz)) creations.put(clazz, buildSQLforTableCreation(clazz));
        return creations.get(clazz);
    }


    private static <T extends DataSet> String fillSQLforDataInsertion(T entity, String statement) {
        Object[] values = new Object[ReflectionHelper.getFields(entity.getClass()).size()];
        int index = 0;
        for (Field field : ReflectionHelper.getFields(entity.getClass())) {
            field.setAccessible(true);
            try {
                values[index++] = field.get(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Err in fillSQLforDataInsertion");
            }
            if (index >= values.length) break;
        }
        return String.format(statement,values);
    }

    private static <T extends DataSet> String buildSQLforDataInsertion(T entity) {
        StringBuilder sb = new StringBuilder("INSERT INTO  ");
        sb.append(entity.getClass().getSimpleName().toUpperCase());
        sb.append(" (");
        sb.append(ReflectionHelper.getFieldNames(entity.getClass(),null));
        sb.append(") VALUES (");
        for (int i = 1; i < ReflectionHelper.getFields(entity.getClass()).size(); i++) {
            sb.append("'%s',");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(");");
        String statement = sb.toString();
        log.info(statement);
        return statement;
    }

    private static String buildSQLforTableCreation(Class clazz) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(clazz.getSimpleName());
        sb.append(" (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,");

        sb.append(ReflectionHelper.getFieldNames(clazz, new Function<Field, String>() {
            @Override
            public String apply(Field field) {
                return accordanceJavaTypeToSqlType(field);
            }
        }));

        sb.append(");");
        String statement = sb.toString().toUpperCase();
        log.info(statement);
        return statement;
    }

        private static String accordanceJavaTypeToSqlType(Field f) {
            if (f.getType().equals(String.class)) return " VARCHAR(255) ";
            if (f.getType().equals(int.class)) return " INT(3) ";
            throw new RuntimeException("Unsupported ORM Type");
        }


}
