package ru.otus.sua.L11.dbservice.dao;

import ru.otus.sua.L11.entity.DataSet;

import java.io.Serializable;

public interface DAO<T extends DataSet, K extends Serializable> {

    K create(T entity);

    T read(K id);

    void update(T entity);

    void delete(T entity);

    T findByName(String name);

}