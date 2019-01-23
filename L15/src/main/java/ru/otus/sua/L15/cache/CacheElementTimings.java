package ru.otus.sua.L15.cache;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheElementTimings {

    private final long creationTime;
    private long       lastAccessTime;

}



