package com.cache_eviction.cacheinterface;

public interface MRUCacheEviction<K,V> {
    public V put(K key, V value);
}
