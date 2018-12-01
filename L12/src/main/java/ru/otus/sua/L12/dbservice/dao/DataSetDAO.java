package ru.otus.sua.L12.dbservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.otus.sua.L12.entity.DataSet;

import java.io.Serializable;
import java.util.function.Supplier;


/**
 * The scope of a Hibernate org.hibernate.Session is flexible but you should never
 * design your application to use a new Hibernate org.hibernate.Session for every
 * database operation. Even though it is used in the following examples, consider
 * session-per-operation an anti-pattern. //TODO make DAO as field in DBServiceImpl
 *
 */
@Getter
@Slf4j
@AllArgsConstructor
public abstract class DataSetDAO<T extends DataSet, K extends Serializable> implements DAO<T, K> {

    private Session session;
    private Class<T> type;

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

    protected <R> R runInTransaction(Supplier<R> supplier) {
        Transaction transaction = session.beginTransaction();
        R result = supplier.get();
        transaction.commit();
        return result;
    }
}

