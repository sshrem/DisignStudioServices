package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.Design;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class DesignRowMapper implements RowMapper<Design> {

    @Override
    public Design mapRow(ResultSet rs, int i) throws SQLException {
        return new Design(rs.getLong("dsds_id"), rs.getLong("dsds_designer_id"), rs.getLong("dsds_apartment_template_id"),
                rs.getString("dsds_title"), rs.getString("dsds_imaging_code"));
    }
}
