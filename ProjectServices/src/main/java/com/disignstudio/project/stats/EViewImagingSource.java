package com.disignstudio.project.stats;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by ohadbenporat on 4/13/16.
 */
public enum EViewImagingSource {

    UNKNOWN(0, "unknown"),
    MOBILE(1, "mobile"),
    SOCIAL(2, "social");


    private final int id;
    private final String source;

    private static Map<String, EViewImagingSource> mapSrcToStatsSrc;

    static {
        mapSrcToStatsSrc = Maps.newHashMap();
        for (EViewImagingSource src : EViewImagingSource.values()) {
            mapSrcToStatsSrc.put(src.source, src);
        }
    }

    EViewImagingSource(int id, String source) {
        this.id = id;
        this.source = source;
    }

    public static EViewImagingSource getStatsSrcBySourceName(String src) {
        EViewImagingSource statsSrc = mapSrcToStatsSrc.get(src);
        return statsSrc == null ? EViewImagingSource.UNKNOWN : statsSrc;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }
}
