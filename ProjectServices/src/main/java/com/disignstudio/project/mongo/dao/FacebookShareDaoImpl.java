package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.FacebookShare;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by shrem on 4/9/17.
 */
public class FacebookShareDaoImpl implements IFacebookShareDao {

    private MongoOperations mongoOperation;

    @Inject
    public FacebookShareDaoImpl(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public void insert(FacebookShare data) {
        mongoOperation.insert(data);
    }
}
