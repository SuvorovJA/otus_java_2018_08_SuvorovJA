package ru.otus.sua.L06.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheSoftHashMap<K, V> extends SoftHashMap<K, V> implements Cache<K, V> {

    private static final int TIME_THRESHOLD_MS = 5;
    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;
    private final Timer timer = new Timer();
    private int hit = 0;
    private int miss = 0;
    // due super.put(key, cacheElement) not work:
    private Map<K, CacheElementTimings> timerHolderCrutch = new HashMap<>();

    public CacheSoftHashMap() {
        this.maxElements = 10;
        this.lifeTimeMs = 0;
        this.idleTimeMs = 0;
        this.isEternal = true;
    }

    public CacheSoftHashMap(int maxElements) {
        this.maxElements = maxElements;
        this.lifeTimeMs = 0;
        this.idleTimeMs = 0;
        this.isEternal = true;
    }

    public CacheSoftHashMap(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = (lifeTimeMs > 0) ? lifeTimeMs : 0;
        this.idleTimeMs = (idleTimeMs > 0) ? idleTimeMs : 0;
        this.isEternal = (lifeTimeMs == 0 && idleTimeMs == 0 || isEternal);
    }

    @Override
    public V put(K key, V value) {
        if (super.size() == maxElements) {
            K keyForRemove = super.first();
            super.remove(keyForRemove);
            timerHolderCrutch.remove(keyForRemove);
        }

        long t = System.currentTimeMillis();
        CacheElementTimings timings = new CacheElementTimings(t, t);
        timerHolderCrutch.put(key, timings);
        V returned = super.put(key, value);

        if (!isEternal) {
            if (lifeTimeMs > 0) {
                TimerTask lifeTimerTask = getTimerTask(key, life -> life.getCreationTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs > 0) {
                TimerTask idleTimerTask = getTimerTask(key, idle -> idle.getLastAccessTime() + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
        return returned;
    }

    @Override
    public V get(Object key) {
        V value = super.get(key);
        if (value != null) {
            hit++;
            timerHolderCrutch.get(key).setLastAccessTime(System.currentTimeMillis());
            return value;
        } else {
            miss++;
            timerHolderCrutch.remove(key);
            return null;
        }
    }

    @Override
    public int getHits() {
        return hit;
    }

    @Override
    public int getMiss() {
        return miss;
    }

    @Override
    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<CacheElementTimings, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                CacheElementTimings timings = timerHolderCrutch.get(key);
                if (timings == null || isT1BeforeT2(timeFunction.apply(timings), System.currentTimeMillis())) {
                    CacheSoftHashMap.super.remove(key);
                    timerHolderCrutch.remove(key);
                    this.cancel();
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
