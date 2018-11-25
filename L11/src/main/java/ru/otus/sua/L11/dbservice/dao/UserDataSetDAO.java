package ru.otus.sua.L11.dbservice.dao;

import ru.otus.sua.L11.entity.DataSet;

import java.io.Serializable;

public class UserDataSetDAO<T extends DataSet, K extends Serializable> extends DataSetDAO<T, K> {

    public UserDataSetDAO(Class<T> type) {
        super(type);
    }

    // class specific 'find' methods

}
