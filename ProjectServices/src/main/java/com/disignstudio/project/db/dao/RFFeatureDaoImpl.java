package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.RFFeature;
import com.disignstudio.project.db.mapper.RFFeatureRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 2/8/16.
 */
public class RFFeatureDaoImpl implements IRFFeatureDao {

    private static final String LOAD_ALL_FEATURES = "select * from rffe_features";

    private JdbcTemplate jdbcTemplate;
    private RFFeatureRowMapper rowMapper;

    @Inject
    public RFFeatureDaoImpl(JdbcTemplate jdbcTemplate, RFFeatureRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<RFFeature> loadAll() {
        return jdbcTemplate.query(LOAD_ALL_FEATURES, rowMapper);
    }
}
