package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.DesignItem;

import java.util.List;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public interface IDesignItemDao {

    public long saveOrUpdate(DesignItem item);

    void insertBatch(List<DesignItem> designItems);

    public List<DesignItem> findByDesign(long designId);

    public List<DesignItem> findByDesignIds(List<Long> designIds);
}
