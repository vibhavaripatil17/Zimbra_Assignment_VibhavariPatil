package com.cache_eviction.cacheimpl;

import java.util.LinkedHashMap;

public class LRUCacheImpl<K,V> extends LinkedHashMap<String, String> {
    private int capacity;

    public LRUCacheImpl(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {

        return size() > capacity;
    }
}
