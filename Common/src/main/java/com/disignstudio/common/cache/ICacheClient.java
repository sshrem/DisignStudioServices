package com.disignstudio.common.cache;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public interface ICacheClient {

    public boolean put(final EntityCacheKey key, final Object value, final int expirationInSeconds);

    public <T> T getOrLoad(Object objKey, ICacheLoader cacheLoader, boolean useCache);

    public String getStoredValue(EntityCacheKey key);

    public boolean clear(final EntityCacheKey key);
}
