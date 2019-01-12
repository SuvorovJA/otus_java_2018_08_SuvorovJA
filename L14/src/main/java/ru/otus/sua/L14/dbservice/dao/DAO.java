package ru.otus.sua.L14.dbservice.dao;

import ru.otus.sua.L14.entity.DataSet;

import java.io.Serializable;
import java.util.List;

public interface DAO<T extends DataSet, K extends Serializable> {

    K create(T entity);

    T read(K id);

    void update(T entity);

    void delete(T entity);

    T findByName(String name);

    long count();

    List<T> readAll();

}