package com.cache_eviction.controller;

import com.cache_eviction.dto.CacheDTO;
import com.cache_eviction.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    CacheService cacheService;

    @PostMapping("/addLruData")
    public String putInLRUCache(@RequestBody CacheDTO cacheDTO) {
        cacheService.putInLRUCache(cacheDTO.getKey(), cacheDTO.getValue());
        return "Key " + cacheDTO.getKey() + " Value added to LRU Cache successfully.";
    }

    @GetMapping("/getLruData/{key}")
    public Optional<String> getFromLRUCache(@PathVariable String key) {
        return Optional.ofNullable(cacheService.getFromLRUCache(key));
    }

    @PostMapping("/addLfuData")
    public String putInLFUCache(@RequestBody CacheDTO cacheDTO) {
        cacheService.putInLFUCache(cacheDTO.getKey(), cacheDTO.getValue());
        return "Key " + cacheDTO.getKey() + " Value added to LFU Cache successfully.";
    }

    @GetMapping("/getLfuData/{key}")
    public Optional<String> getFromLFUCache(@PathVariable String key) {
        return Optional.ofNullable(cacheService.getFromLFUCache(key));
    }

    @PostMapping("/addMruData")
    public String putInMRUCache(@RequestBody CacheDTO cacheDTO) {
        cacheService.putInMRUCache(cacheDTO.getKey(), cacheDTO.getValue());
        return "Key " + cacheDTO.getKey() + " Value added to MRU Cache successfully.";
    }

    @GetMapping("/getMruData/{key}")
    public Optional<String> getFromMRUCache(@PathVariable String key) {
        return Optional.ofNullable(cacheService.getFromMRUCache(key));
    }

}
