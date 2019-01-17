package ru.otus.sua.L14.dbservice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L06.Cache.Cache;
import ru.otus.sua.L14.entity.DataSet;

import java.sql.SQLException;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class CachedDBServiceHibernateImpl extends DBServiceHibernateImpl {

    private Cache<Long, DataSet> cacheDataSet;

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
