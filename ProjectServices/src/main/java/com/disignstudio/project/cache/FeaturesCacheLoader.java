package com.disignstudio.project.cache;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.common.cache.ICacheLoader;
import com.disignstudio.project.db.bean.RFFeature;
import com.disignstudio.project.db.dao.IRFFeatureDao;
import com.disignstudio.project.loader.data.FeatureData;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 2/8/16.
 */
public class FeaturesCacheLoader implements ICacheLoader {

    private final static String CACHE_PREFIX_KEY = "FEATURES_METADATA_CACHE_KEY2";
    private final int cacheExpiration;
    private IRFFeatureDao featureDao;

    @Inject
    public FeaturesCacheLoader(@Named("featuresCacheExpiration") int cacheExpiration, IRFFeatureDao featureDao) {
        this.cacheExpiration = cacheExpiration;
        this.featureDao = featureDao;
    }

    @Override
    public FeatureData load(Object key) {
        List<RFFeature> features = featureDao.loadAll();

        Map<String, String> mapLanguageToTranslationEN = Maps.newHashMap();
        Map<String, String> mapLanguageToTranslationHE = Maps.newHashMap();
        features.forEach(feat -> {
            mapLanguageToTranslationEN.put(feat.getCode(), feat.getNameEN());
            mapLanguageToTranslationHE.put(feat.getCode(), feat.getNameHE());
        });

        FeatureData data = new FeatureData();
        data.addLanguage("en", mapLanguageToTranslationEN);
        data.addLanguage("he", mapLanguageToTranslationHE);
        return data;
    }

    @Override
    public int getCacheExpiration() {
        return cacheExpiration;
    }

    @Override
    public TypeToken<FeatureData> getCacheValueType() {
        return TypeToken.of(FeatureData.class);
    }

    @Override
    public EntityCacheKey<String> generateKey(Object keyEntity) {
        return new EntityCacheKey<>((String) keyEntity, CACHE_PREFIX_KEY);
    }
}
