package com.disignstudio.common.cache;

import com.google.common.reflect.TypeToken;

/**
 * Created by ohadbenporat on 2/2/16.
 */
public interface ICacheLoader {

    public <V> V load(Object objKey);

    public int getCacheExpiration();

    public <K> EntityCacheKey<K> generateKey(Object keyEntity);

    public <K> TypeToken<K> getCacheValueType();
}
