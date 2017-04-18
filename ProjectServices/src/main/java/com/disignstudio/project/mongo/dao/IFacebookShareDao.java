package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.api.response.CollectionStats;
import com.disignstudio.project.mongo.bean.FacebookShare;

/**
 * Created by shrem on 4/9/17.
 */
public interface IFacebookShareDao {
    public void insert(FacebookShare data);
    CollectionStats getStats(Long projectId);
}
