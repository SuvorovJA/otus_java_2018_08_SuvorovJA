package ru.otus.sua.L15.cache;

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
