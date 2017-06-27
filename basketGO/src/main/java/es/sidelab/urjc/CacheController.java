package es.sidelab.urjc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

	@Autowired
	private CacheManager cacheManager;

	@RequestMapping(value="/cache")
	public Map<Object, Object> getCacheContent(){
		ConcurrentMapCacheManager concurrentManager = (ConcurrentMapCacheManager) cacheManager;
		ConcurrentMapCache cache = (ConcurrentMapCache) concurrentManager.getCache("cacheLiga");
		return cache.getNativeCache();
	}

}
