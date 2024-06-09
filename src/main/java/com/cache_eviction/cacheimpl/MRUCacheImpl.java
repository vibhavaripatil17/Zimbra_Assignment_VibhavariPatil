package com.cache_eviction.cacheimpl;

import com.cache_eviction.cacheinterface.MRUCacheEviction;

import java.util.LinkedHashMap;

public class MRUCacheImpl<K,V> extends LinkedHashMap<K,V> implements MRUCacheEviction<K,V> {
    private final int capacity;

    public MRUCacheImpl(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K,V> eldest) {
        return false;
    }

    @Override
    public V put(K key, V value) {
        if (size() >= capacity) {
            K mostRecentKey = getMostRecentKey();
            if(mostRecentKey != null)
                remove(mostRecentKey);
        }
        return super.put(key, value);
    }


    private K getMostRecentKey() {
        K mostRecentKey = null;
        for (K key : keySet()) {
            mostRecentKey = key;
        }
        return mostRecentKey;
    }
}
