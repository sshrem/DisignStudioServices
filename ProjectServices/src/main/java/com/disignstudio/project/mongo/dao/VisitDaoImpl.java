package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.Visit;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by shrem on 4/18/17.
 */
public class VisitDaoImpl implements IVisitDao {
    private MongoOperations mongoOperation;

    @Inject
    public VisitDaoImpl(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public void insert(Visit data) {
        mongoOperation.insert(data);
    }
}
