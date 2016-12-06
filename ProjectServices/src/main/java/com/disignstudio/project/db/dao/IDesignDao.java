package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Design;
import com.disignstudio.project.db.bean.extended.DesignExtended;

import java.util.List;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public interface IDesignDao {


    public long saveOrUpdate(Design design);

    public Design findById(long id);

    public List<DesignExtended> findByApartmentTemplateId(long roomId);
}
