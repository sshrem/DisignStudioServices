package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.ProjectFeature;
import com.disignstudio.project.db.mapper.ProjectFeatureRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public class ProjectFeatureDaoImpl implements IProjectFeatureDao {

    private static final String ADD_PROJECT_FEATURE_QUERY = "insert into dspf_project_features (dspf_project_id, dspf_feature_id, dspf_description) values (%d,%d,'%s')";
    private static final String FIND_PROJECT_EXTENDED_BY_ID_QUERY = "select pf.*,rf.rffe_code from dspf_project_features pf join rffe_features rf on dspf_feature_id = rffe_id where pf.dspf_project_id = %d";

    private JdbcTemplate jdbcTemplate;
    private ProjectFeatureRowMapper rowMapper;

    @Inject
    public ProjectFeatureDaoImpl(JdbcTemplate jdbcTemplate, ProjectFeatureRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void saveOrUpdate(ProjectFeature projectFeature) {
        String query = String.format(ADD_PROJECT_FEATURE_QUERY, projectFeature.getProjectId(), projectFeature.getFeatureId(), projectFeature.getFeatureDescription());
        jdbcTemplate.execute(query);
    }

    @Override
    public List<ProjectFeature> findByProjectId(long projectId) {
        String query = String.format(FIND_PROJECT_EXTENDED_BY_ID_QUERY, projectId);
        return jdbcTemplate.query(query, rowMapper);
    }
}
