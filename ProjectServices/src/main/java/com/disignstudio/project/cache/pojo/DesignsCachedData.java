package com.disignstudio.project.cache.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ohadbenporat on 5/11/16.
 */
public class DesignsCachedData implements Serializable {

    private List<DesignCachedData> designs;

    public DesignsCachedData(List<DesignCachedData> designs) {
        this.designs = designs;
    }

    public List<DesignCachedData> getDesigns() {
        return designs;
    }

    public void setDesigns(List<DesignCachedData> designs) {
        this.designs = designs;
    }
}
