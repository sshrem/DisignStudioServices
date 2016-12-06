package com.disignstudio.common.cache;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sun.javafx.fxml.expression.Expression;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;

/**
 * Created by ohadbenporat on 3/23/16.
 */
public class RedisCacheClient implements ICacheClient {

    private final JedisPool redisPool;
    private final Gson gson;

    @Inject
    public RedisCacheClient(URI jedisUri, Gson gson) {
        this.redisPool = new JedisPool(new JedisPoolConfig(), jedisUri);
        this.gson = gson;
    }

    @Override
    public boolean put(EntityCacheKey key, Object value, int expirationInSeconds) {
        String valueToStore = gson.toJson(value);

        Jedis jedis = redisPool.getResource();
        jedis.setex(key.toString(), expirationInSeconds, valueToStore);
        jedis.close();
        return true;
    }

    @Override
    public <T> T getOrLoad(Object objKey, ICacheLoader cacheLoader, boolean useCache) {


        EntityCacheKey key = cacheLoader.generateKey(objKey);
        TypeToken<T> type = cacheLoader.getCacheValueType();
        if (useCache) {
            T value = get(key, type);
            if (value != null) {
                return value;
            }
        }


        T freshValue = cacheLoader.load(objKey);
        put(key, freshValue, cacheLoader.getCacheExpiration());
        return freshValue;
    }

    private <T> T get(EntityCacheKey key, TypeToken<T> type) {
        Jedis jedis = redisPool.getResource();
        String json = jedis.get(key.toString());
        jedis.close();
        return gson.fromJson(json, type.getType());
    }

    @Override
    public String getStoredValue(EntityCacheKey key) {
        Jedis jedis = redisPool.getResource();
        String json = jedis.get(key.toString());
        jedis.close();
        return json;
    }

    @Override
    public boolean clear(EntityCacheKey key) {
        Jedis jedis = redisPool.getResource();
        long numKeyRemoved = jedis.del(key.toString());
        return numKeyRemoved > 0;
    }
}
