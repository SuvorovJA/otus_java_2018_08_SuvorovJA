package ru.otus.sua.L11.dbservice;

import ru.otus.sua.L11.entity.DataSet;

import java.sql.SQLException;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void createTables(Class clazz) throws SQLException;

    String getName(long id, Class clazz) throws SQLException;

    <T extends DataSet> void save(T entity) throws SQLException;

    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException;

}