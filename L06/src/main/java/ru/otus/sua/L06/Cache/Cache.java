package ru.otus.sua.L06.Cache;

public interface Cache<K, V> {

    V put(K key, V value);

    V get(Object key);

    int getHits();

    int getMiss();

    void dispose();

}