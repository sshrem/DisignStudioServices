package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.RFFeature;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/8/16.
 */
public class RFFeatureRowMapper implements RowMapper<RFFeature> {

    @Override
    public RFFeature mapRow(ResultSet rs, int i) throws SQLException {
        return new RFFeature(rs.getLong("rffe_id"), rs.getString("rffe_code"), rs.getString("rffe_name_en"), rs.getString("rffe_name_he"));
    }
}
