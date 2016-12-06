package com.disignstudio.common.cache;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public class EntityCacheKey<T> implements Serializable{

    private T entityKey;
    private String prefixKey;

    public EntityCacheKey(T entityKey, String prefixKey) {
        this.entityKey = entityKey;
        this.prefixKey = prefixKey;
    }

    public T getEntityKey() {
        return entityKey;
    }

    public String getPrefixKey() {
        return prefixKey;
    }

    @Override
    public String toString() {
        return this.prefixKey.concat("_").concat(this.entityKey.toString());
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(prefixKey, entityKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final EntityCacheKey other = (EntityCacheKey) obj;

        return Objects.equal(this.prefixKey, other.prefixKey) && Objects.equal(this.entityKey, other.entityKey);
    }
}
