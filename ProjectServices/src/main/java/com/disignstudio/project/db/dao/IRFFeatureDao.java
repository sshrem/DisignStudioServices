package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.RFFeature;

import java.util.List;

/**
 * Created by ohadbenporat on 2/8/16.
 */
public interface IRFFeatureDao {

    public List<RFFeature> loadAll();
}
