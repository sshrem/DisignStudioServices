package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.VideoView;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by shrem on 4/9/17.
 */
public class VideoViewDaoImpl extends MongoDao<VideoView>  implements IVideoViewDao {
    public static final String COLLECTION_NAME = "video_view";

    @Inject
    public VideoViewDaoImpl(MongoOperations mongoOperation) {
        super(mongoOperation);
    }

    public String getCollectionName() {
        return COLLECTION_NAME;
    }
}
