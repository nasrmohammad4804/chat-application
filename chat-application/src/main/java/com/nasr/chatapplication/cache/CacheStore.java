package com.nasr.chatapplication.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class CacheStore<T> {

    private final Cache<String, T> cacheData;

    public CacheStore(int expirationDuration, TimeUnit timeUnit) {
        cacheData = CacheBuilder.newBuilder()
                .expireAfterWrite(expirationDuration, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public Optional<T> getValue(String key) {
        T value = cacheData.getIfPresent(key);
        return value == null ? Optional.empty() : Optional.of(value);
    }

    public void add(String key, T value) {
        if (key == null || value == null)
            throw new IllegalStateException("key and value must not null !");

        cacheData.put(key, value);
    }


}
