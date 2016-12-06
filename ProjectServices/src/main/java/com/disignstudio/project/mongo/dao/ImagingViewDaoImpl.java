package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.ImagingView;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by ohadbenporat on 3/31/16.
 */
public class ImagingViewDaoImpl implements IImagingViewDao {

    private MongoOperations mongoOperation;

    @Inject
    public ImagingViewDaoImpl(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public void insert(ImagingView data) {
        mongoOperation.insert(data);
    }
}
