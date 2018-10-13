package ru.otus.sua.L06.Cache;

import org.apache.log4j.Logger;

import java.util.stream.IntStream;

public class Main {

    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException {
        int size = 100_000;
        log.info("All tests with cache size =" + size);
        {
            log.info("Test Eternal Cache");
            Cache<Integer, String> cache = new CacheSoftHashMap<>(size, 0, 0, true);
            IntStream.range(0, size + size).forEach(i -> cache.put(i, "Test" + i));
            PrintCacheContent(cache, size + size);
            log.info("Hits=" + cache.getHits() + " Miss=" + cache.getMiss());
            cache.dispose();
        }
        {
            int sleep = 550;
            int life = 555;
            log.info("Test Life Timing Cache ms=" + life);
            Cache<Integer, String> cache = new CacheSoftHashMap<>(size, life, 0, false);
            IntStream.range(0, size).forEach(i -> cache.put(i, "Test" + i));
            PrintCacheContent(cache, size);
            log.info("sleepint for ms=" + sleep);
            Thread.sleep(sleep);
            PrintCacheContent(cache, size);
            log.info("Hits=" + cache.getHits() + " Miss=" + cache.getMiss());
            cache.dispose();
        }
        {
            int sleep = 490;
            int idle = 500;
            log.info("Test Idle Timing Cache ms=" + idle);
            Cache<Integer, String> cache = new CacheSoftHashMap<>(size, 0, idle, false);
            IntStream.range(0, size).forEach(i -> cache.put(i, "Test" + i));
            PrintCacheContent(cache, size);
            log.info("sleepint for ms=" + sleep);
            Thread.sleep(sleep);
            PrintCacheContent(cache, size);
            log.info("sleepint for ms=" + (sleep + sleep));
            Thread.sleep(sleep + sleep);
            PrintCacheContent(cache, size);
            log.info("Hits=" + cache.getHits() + " Miss=" + cache.getMiss());
            cache.dispose();
        }
    }

    private static void PrintCacheContent(Cache<Integer, String> cache, int rangesize) {
        IntStream.range(0, rangesize)
                .mapToObj(i -> cache.get(i))
                .forEach(s -> log.info(s == null ? "missing" : "value is " + s));
    }
}
