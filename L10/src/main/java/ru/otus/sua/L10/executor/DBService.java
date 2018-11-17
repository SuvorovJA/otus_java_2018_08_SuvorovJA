package ru.otus.sua.L10.executor;

import ru.otus.sua.L10.entity.DataSet;

import java.sql.SQLException;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void createTables(Class clazz) throws SQLException;

    String getUserName(long id, Class clazz) throws SQLException;

    <T extends DataSet> void save(T user) throws SQLException;

    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException;

}