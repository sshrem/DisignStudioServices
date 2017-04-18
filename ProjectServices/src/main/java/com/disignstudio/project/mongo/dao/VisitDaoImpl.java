package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.Visit;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by shrem on 4/18/17.
 */
public class VisitDaoImpl extends MongoDao<Visit> implements IVisitDao {
    public static final String COLLECTION_NAME = "visits";

    @Inject
    public VisitDaoImpl(MongoOperations mongoOperation) {
        super(mongoOperation);
    }

    @Override
    protected String getCollectionName() {
        return COLLECTION_NAME;
    }
}
