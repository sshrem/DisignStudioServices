package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.api.response.CollectionStats;
import com.disignstudio.project.mongo.bean.VideoView;

/**
 * Created by shrem on 4/9/17.
 */
public interface IVideoViewDao {
    public void insert(VideoView data);

    CollectionStats getStats(Long projectId);
}
