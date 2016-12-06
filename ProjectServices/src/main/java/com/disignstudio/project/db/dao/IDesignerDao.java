package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Designer;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public interface IDesignerDao {

    public void saveOrUpdate(Designer designer);

    public Designer findById(long id);
}
