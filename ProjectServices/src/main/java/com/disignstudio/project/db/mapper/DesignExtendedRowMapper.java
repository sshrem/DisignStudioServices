package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.extended.DesignExtended;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/15/16.
 */
public class DesignExtendedRowMapper implements RowMapper<DesignExtended> {
    @Override
    public DesignExtended mapRow(ResultSet rs, int i) throws SQLException {
        return new DesignExtended(rs.getLong("dsds_id"), rs.getLong("dsds_designer_id"),
                rs.getLong("dsds_apartment_template_id"), rs.getString("dsds_title"), rs.getString("dsds_imaging_code"),
                rs.getString("dsco_name"), rs.getString("dsde_image"));
    }
}
