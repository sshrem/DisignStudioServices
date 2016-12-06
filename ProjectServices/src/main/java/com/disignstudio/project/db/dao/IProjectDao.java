package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Project;
import com.disignstudio.project.db.bean.extended.ProjectExtended;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public interface IProjectDao {

    public void saveOrUpdate(Project project);

    public ProjectExtended findById(long id);

    public ProjectExtended findByCode(String code);
}
