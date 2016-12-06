package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.ProjectFeature;

import java.util.List;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public interface IProjectFeatureDao {

    public void saveOrUpdate(ProjectFeature project);

    public List<ProjectFeature> findByProjectId(long projectId);
}
