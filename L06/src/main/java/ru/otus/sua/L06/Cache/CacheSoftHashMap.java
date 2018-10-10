package ru.otus.sua.L06.Cache;

public class CacheSoftHashMap<K, V> extends SoftHashMap<K, V> implements Cache<K, V> {

    @Override
    public V put(K key, V value) {
        return super.put(key,value);
    }

    @Override
    public V get(Object key){
        return super.get(key);
    }

    @Override
    public int getHits() {
        return 0;
    }

    @Override
    public int getMiss() {
        return 0;
    }

    @Override
    public void dispose() {

    }


}
