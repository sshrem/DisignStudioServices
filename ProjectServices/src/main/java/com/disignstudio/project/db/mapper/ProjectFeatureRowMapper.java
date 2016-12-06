package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.ProjectFeature;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public class ProjectFeatureRowMapper implements RowMapper<ProjectFeature> {

    @Override
    public ProjectFeature mapRow(ResultSet rs, int i) throws SQLException {
        return new ProjectFeature(rs.getLong("dspf_project_id"), rs.getInt("dspf_feature_id"), rs.getString("dspf_description"), rs.getString("rffe_code"));
    }
}
