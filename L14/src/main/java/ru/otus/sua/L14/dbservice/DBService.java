package ru.otus.sua.L14.dbservice;

import ru.otus.sua.L14.entity.DataSet;

import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void createTables(Class clazz) throws SQLException;

    @Deprecated
    String getName(long id, Class clazz) throws SQLException;

    <T extends DataSet> T  getByName(String name, Class clazz) throws SQLException;

    <T extends DataSet> void save(T entity) throws SQLException;

    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException;

    <T extends DataSet> List<T> loadAll(Class<T> clazz) throws SQLException;

    <T extends DataSet> long count(Class<T> clazz) throws SQLException;

}