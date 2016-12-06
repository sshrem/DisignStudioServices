package com.disignstudio.project.loader.data;


import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by ohadbenporat on 2/8/16.
 */
public class FeatureData implements Serializable {

    private Map<String, Map<String, String>> translations;

    public FeatureData() {
        this.translations = Maps.newHashMap();
    }

    public void addLanguage(String lang, Map<String, String> words) {
        translations.put(lang, words);
    }

    public Map<String, Map<String, String>> getTranslations() {
        return translations;
    }
}
