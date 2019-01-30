package ru.otus.sua.L16.cache;

import lombok.extern.slf4j.Slf4j;
import ru.otus.sua.L16.entity.DataSet;
import ru.otus.sua.L16.starting.Startup;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class CacheBean extends CacheSoftHashMap<Long, DataSet> implements Startup {

    public CacheBean(){
        this(10,2222,2345,false);
    }

    private CacheBean(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal){
        super(maxElements, lifeTimeMs, idleTimeMs, isEternal);
    }

}
