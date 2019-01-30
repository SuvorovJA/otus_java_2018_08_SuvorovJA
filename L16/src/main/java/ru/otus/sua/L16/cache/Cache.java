package ru.otus.sua.L16.cache;

public interface Cache<K, V> {

    V put(K key, V value);

    V get(Object key);

    int getHits();

    int getMiss();

    void dispose();

}