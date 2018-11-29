package ru.otus.sua.L11.dbservice.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.otus.sua.L11.entity.DataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public class UserDataSetDAO<T extends DataSet, K extends Serializable> extends DataSetDAO<T, K> {

    public UserDataSetDAO(Session session, Class<T> type) {
        super(session, type);
    }

    @Override
    public T findByName(String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getType());
        Root<T> from = criteria.from(getType());
        criteria.where(builder.equal(from.get("name"), name));
        Query<T> query = getSession().createQuery(criteria);
        return query.uniqueResult();
    }
}


