package com.cache_eviction;

import com.cache_eviction.service.CacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class CacheEvictionApplicationTests {

	@Autowired
	private CacheService cacheService;


	@Test
	 void testLRUCache() {
		cacheService.putInLRUCache("1", "one");
		cacheService.putInLRUCache("2", "two");
		cacheService.putInLRUCache("3", "three");
		cacheService.putInLRUCache("4", "four");

		assertNull(cacheService.getFromLRUCache("1"));
		assertEquals("two", cacheService.getFromLRUCache("2"));
		assertEquals("three", cacheService.getFromLRUCache("3"));
		assertEquals("four", cacheService.getFromLRUCache("4"));


		cacheService.putInLRUCache("1", "one");
		cacheService.putInLRUCache("2", "two");
		cacheService.putInLRUCache("3", "three");

		assertEquals("one", cacheService.getFromLRUCache("1"));

		cacheService.putInLRUCache("4", "four");
		assertNull(cacheService.getFromLRUCache("2"));
		assertEquals("three", cacheService.getFromLRUCache("3"));
		assertEquals("four", cacheService.getFromLRUCache("4"));


	}

	@Test
	void testLFUCache() {
		cacheService.putInLFUCache("1", "one");
		cacheService.putInLFUCache("2", "two");
		cacheService.putInLFUCache("3", "three");
		cacheService.getFromLFUCache("1");
		cacheService.getFromLFUCache("1");
		cacheService.getFromLFUCache("3");
		cacheService.putInLFUCache("4", "four");

		assertEquals("one", cacheService.getFromLFUCache("1"));
		assertEquals("three", cacheService.getFromLFUCache("3"));
		assertNull(cacheService.getFromLFUCache("2"));
		assertEquals("four", cacheService.getFromLFUCache("4"));
	}

	@Test
	void testMRUCache() {
		cacheService.putInMRUCache("1", "one");
		cacheService.putInMRUCache("2", "two");
		cacheService.putInMRUCache("3", "three");
		cacheService.putInMRUCache("4", "four");

		assertEquals("one", cacheService.getFromMRUCache("1"));
		assertEquals("two", cacheService.getFromMRUCache("2"));
		assertNull(cacheService.getFromMRUCache("3"));
		assertEquals("four", cacheService.getFromMRUCache("4"));

		cacheService.putInMRUCache("5", "five");
		assertNull(cacheService.getFromMRUCache("4"));
	}
}
