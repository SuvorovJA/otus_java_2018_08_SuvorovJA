package ru.otus.sua.L10.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.sua.L10.entity.DataSet;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SqlStatementBuilder {

    private static Map<Class<?>, String> insertions = new ConcurrentHashMap<>();
    private static Map<String, Integer> insertionsValueCount = new ConcurrentHashMap<>();
    private static Map<Class<?>, String> creations = new ConcurrentHashMap<>();

    private static Logger log = LoggerFactory.getLogger(SqlStatementBuilder.class);

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
        Object[] values = new Object[insertionsValueCount.get(statement)];
        int index = 0;
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                values[index] = field.get(entity);
                index++;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Err in buildSQLforDataInsertion");
            }
            if (index >= values.length) break;
        }
        return String.format(statement,values);
    }

    private static <T extends DataSet> String buildSQLforDataInsertion(T entity) {
        StringBuilder sb = new StringBuilder("INSERT INTO  ");
        sb.append(entity.getClass().getSimpleName().toUpperCase());
        sb.append(" (");
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) continue;
            sb.append(field.getName().toUpperCase());
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(") VALUES (");
        int valueCount = 0;
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) continue;
            sb.append("'%s',");
            valueCount++;
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(");");
        String statement = sb.toString();
        log.info(statement);
        insertionsValueCount.put(statement, valueCount);
        return statement;
    }


    private static String buildSQLforTableCreation(Class clazz) {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(clazz.getSimpleName());
        sb.append(" (ID BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,");
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if ("id".equals(field.getName())) continue;
            sb.append(field.getName());
            sb.append(accordanceJavaTypeToSqlType(field));
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
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
