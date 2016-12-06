package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.UserAction;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by ohadbenporat on 3/31/16.
 */
public class UserActionDaoImpl implements IUserActionDao {

    private MongoOperations mongoOperation;

    @Inject
    public UserActionDaoImpl(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public void insert(UserAction data) {
        mongoOperation.insert(data);
    }
}
