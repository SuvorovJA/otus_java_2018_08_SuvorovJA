package ru.otus.sua.L15.dbservice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L15.cache.CacheBean;
import ru.otus.sua.L15.entity.DataSet;
import ru.otus.sua.L15.starting.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.SQLException;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ApplicationScoped
@CurrentDbService
public class CachedDBServiceHibernateImpl extends DBServiceHibernateImpl implements Startup {

    @Inject
    private CacheBean cacheDataSet;

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException {
        DataSet data = cacheDataSet.get(id);
        if (data == null) {
            data = super.load(id, clazz);
            if (data != null) cacheDataSet.put(id, data);
        }
        log.info("Cache hits: {}, miss: {}",
                cacheDataSet.getHits(),
                cacheDataSet.getMiss());
        return (T) data;
    }

}
