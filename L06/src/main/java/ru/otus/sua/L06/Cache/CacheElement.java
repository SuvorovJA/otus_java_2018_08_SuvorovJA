package ru.otus.sua.L06.Cache;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheElement<K, V> {

    private final K key;
    private final V value;
    private final long creationTime;
    private long  lastAccessTime;

}
