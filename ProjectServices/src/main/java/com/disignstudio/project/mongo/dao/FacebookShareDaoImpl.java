package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.FacebookShare;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by shrem on 4/9/17.
 */
public class FacebookShareDaoImpl extends MongoDao<FacebookShare>  implements IFacebookShareDao {
    public static final String COLLECTION_NAME = "visits";

    @Inject
    public FacebookShareDaoImpl(MongoOperations mongoOperation) {
        super(mongoOperation);
    }

    @Override
    protected String getCollectionName() {
        return COLLECTION_NAME;
    }

}
