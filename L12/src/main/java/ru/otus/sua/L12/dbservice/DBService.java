package ru.otus.sua.L12.dbservice;

import ru.otus.sua.L12.entity.DataSet;

import java.sql.SQLException;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void createTables(Class clazz) throws SQLException;

    @Deprecated
    String getName(long id, Class clazz) throws SQLException;

    <T extends DataSet> T  getByName(String name, Class clazz) throws SQLException;

    <T extends DataSet> void save(T entity) throws SQLException;

    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException;

}