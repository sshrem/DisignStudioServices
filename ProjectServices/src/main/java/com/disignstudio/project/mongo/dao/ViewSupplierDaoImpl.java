package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.mongo.bean.ViewSupplier;
import com.google.inject.Inject;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by ohadbenporat on 5/2/16.
 */
public class ViewSupplierDaoImpl implements IViewSupplierDao {

    private MongoOperations mongoOperation;

    @Inject
    public ViewSupplierDaoImpl(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public void insert(ViewSupplier data) {
        mongoOperation.insert(data);
    }
}
