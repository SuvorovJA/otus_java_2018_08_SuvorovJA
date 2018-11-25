package ru.otus.sua.L11.dbservice.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.otus.sua.L11.dbservice.database.HibernateFactorySessionHolder;
import ru.otus.sua.L11.entity.DataSet;

import java.io.Serializable;
import java.util.function.Supplier;

public abstract class DataSetDAO<T extends DataSet, K extends Serializable> implements DAO<T, K> {

    private Session session;
    private Class<T> type;

    public DataSetDAO(Class<T> type) {
        session = HibernateFactorySessionHolder.getSession();
        this.type = type;
    }

    @Override
    public K create(T entity) {
        return runInTransaction(() -> (K) session.save(entity));
    }

    @Override
    public T read(K id) {
        return runInTransaction(() -> (T) session.get(type, id));
    }

    @Override
    public void update(T entity) {
        runInTransaction(() -> {
            session.update(entity);
            return null;
        });
    }

    @Override
    public void delete(T entity) {
        runInTransaction(() -> {
            session.delete(entity);
            return null;
        });
    }

    @Override
    public void close() throws Exception {
        session.close();
    }

    protected <R> R runInTransaction(Supplier<R> supplier) {
        Transaction transaction = session.beginTransaction();
        R result = supplier.get();
        transaction.commit();
        return result;
    }
}

