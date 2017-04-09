package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.VideoView;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by shrem on 4/9/17.
 */
public class VideoViewDaoImpl implements IVideoViewDao {
    private MongoOperations mongoOperation;

    @Inject
    public VideoViewDaoImpl(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public void insert(VideoView data) {
        mongoOperation.insert(data);
    }
}
