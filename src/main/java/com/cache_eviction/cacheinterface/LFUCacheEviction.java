package com.cache_eviction.cacheinterface;

public interface LFUCacheEviction<K,V> {

    public V get(K key);
    public void put(K key, V value);
}
