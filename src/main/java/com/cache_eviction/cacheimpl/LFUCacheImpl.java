package com.cache_eviction.cacheimpl;

import com.cache_eviction.cacheinterface.LFUCacheEviction;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class LFUCacheImpl<K,V> implements LFUCacheEviction<K,V> {

    private final int capacity;
    private Map<K, V> cache;
    private Map<K, Integer> counts;
    private TreeMap<Integer, LinkedHashSet<K>> frequencies;


    public LFUCacheImpl(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        counts = new HashMap<>();
        frequencies = new TreeMap<>();
        frequencies.put(1, new LinkedHashSet<>());
    }

        @Override
        public V get(K key) {
            if (!cache.containsKey(key)) {
                return null;
            }
            int count = counts.get(key);
            counts.put(key, count + 1);
            frequencies.get(count).remove(key);
            if (frequencies.get(count).isEmpty()) {
                frequencies.remove(count);
            }
            frequencies.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);
            return cache.get(key);
        }

        @Override
        public void put(K key, V value) {
            if (capacity <= 0) {
                return;
            }
            if (cache.containsKey(key)) {
                cache.put(key, value);
                get(key);
                return;
            }
            if (cache.size() >= capacity) {
                int lowestFreq = frequencies.firstKey();
                K evict = frequencies.get(lowestFreq).iterator().next();
                frequencies.get(lowestFreq).remove(evict);
                if (frequencies.get(lowestFreq).isEmpty()) {
                    frequencies.remove(lowestFreq);
                }
                cache.remove(evict);
                counts.remove(evict);
            }
            cache.put(key, value);
            counts.put(key, 1);
            frequencies.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        }
}



