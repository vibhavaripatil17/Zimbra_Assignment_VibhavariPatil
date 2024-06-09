package com.cache_eviction.service;

import com.cache_eviction.cacheimpl.LRUCacheImpl;
import com.cache_eviction.cacheimpl.LFUCacheImpl;
import com.cache_eviction.cacheimpl.MRUCacheImpl;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private LRUCacheImpl<String, String> lruCache;
    private LFUCacheImpl<String, String> lfuCache;
    private MRUCacheImpl<String, String> mruCache;

    public CacheService() {
        this.lruCache = new LRUCacheImpl<>(3);
        this.lfuCache = new LFUCacheImpl<>(3);
        this.mruCache = new MRUCacheImpl<>(3);
    }


    public void putInLRUCache(String key, String value) {
        lruCache.put(key, value);
    }

    public String getFromLRUCache(String key) {
        return lruCache.get(key);
    }

    public void putInLFUCache(String key, String value) {
        lfuCache.put(key, value);
    }

    public String getFromLFUCache(String key) {
        return lfuCache.get(key);
    }

    public void putInMRUCache(String key, String value) {
        mruCache.put(key, value);
    }

    public String getFromMRUCache(String key) {
        return mruCache.get(key);
    }
}
