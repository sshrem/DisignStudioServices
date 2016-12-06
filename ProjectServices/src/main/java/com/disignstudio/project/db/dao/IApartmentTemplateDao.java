package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.ApartmentTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public interface IApartmentTemplateDao {

    public long saveOrUpdate(ApartmentTemplate apartmentTemplate);

    public ApartmentTemplate findById(long id);

    public List<ApartmentTemplate> findByProject(long projectId);

    public ApartmentTemplate findByCode(String code);
}
