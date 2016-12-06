package com.disignstudio.common.cache;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by ohadbenporat on 2/2/16.
 */
public class InProcCacheClient implements ICacheClient {

    private final CacheManager cacheManager;
    private final String cacheName;
    private final Gson gson;

    public InProcCacheClient(String cacheName, Gson gson) {
        cacheManager = CacheManager.create();
        if (!cacheManager.cacheExists(cacheName)) {
            cacheManager.addCache(cacheName);
        }
        this.cacheName = cacheName;
        this.gson = gson;
    }

    @Override
    public boolean put(final EntityCacheKey key, final Object value, final int expirationInSeconds) {

        final Element envelope = new Element(key, gson.toJson(value));
        envelope.setTimeToLive(expirationInSeconds);
        cacheManager.getCache(cacheName).put(envelope);
        return true;
    }

    @Override
    public <T> T getOrLoad(Object objKey, ICacheLoader cacheLoader, boolean useCache) {

        EntityCacheKey key = cacheLoader.generateKey(objKey);
        TypeToken<T> type = cacheLoader.getCacheValueType();
        if (useCache){
            T data = get(key, type);
            if (data != null) {
                return data;
            }
        }


        T loadedData = cacheLoader.load(objKey);
        put(key, loadedData, cacheLoader.getCacheExpiration());
        return loadedData;
    }

    private <T> T get(EntityCacheKey key, TypeToken<T> type) {
        Element envelope = cacheManager.getCache(cacheName).get(key);
        if (envelope != null) {
            return gson.fromJson((String) envelope.getObjectValue(), type.getType());
        }

        return null;
    }

    @Override
    public String getStoredValue(EntityCacheKey key) {
        Element envelope = cacheManager.getCache(cacheName).get(key);
        if (envelope != null) {
            return (String) envelope.getObjectValue();
        }

        return null;
    }

    @Override
    public boolean clear(EntityCacheKey key) {
        return cacheManager.getCache(cacheName).remove(key);
    }
}
