package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Entreprenuer;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public interface IEntrepreneurDao {

    public void saveOrUpdate(Entreprenuer entreprenuer);

    public Entreprenuer findById(long id);
}
