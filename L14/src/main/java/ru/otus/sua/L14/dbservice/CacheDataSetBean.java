package ru.otus.sua.L14.dbservice;

import lombok.Data;
import ru.otus.sua.L06.Cache.CacheSoftHashMap;
import ru.otus.sua.L14.entity.DataSet;

@Data
public class CacheDataSetBean {

    // TODO: make thread safe (not for "prototype" bean scope)

    private CacheSoftHashMap<Long, DataSet> dataSetCache;

    private void init(){
        dataSetCache = new CacheSoftHashMap<>(10,2000,2000,false);
    }

}
