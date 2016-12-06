package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.extended.ProjectExtended;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/7/16.
 */
public class ProjectExtendedRowMapper implements RowMapper<ProjectExtended> {

    @Override
    public ProjectExtended mapRow(ResultSet rs, int i) throws SQLException {
        return new ProjectExtended(rs.getLong("dspr_id"), rs.getString("dspr_code"), rs.getLong("dspr_entrepreneur_id"), rs.getString("dspr_name"), rs.getString("dspr_address"),
                rs.getInt("dspr_city"), rs.getInt("dspr_country"), rs.getDouble("dspr_lon"), rs.getDouble("dspr_lat"), rs.getBoolean("dspr_is_active"),
                rs.getString("rfci_name"), rs.getString("rfco_name"), rs.getString("dspr_logo"), rs.getString("dspr_about"), rs.getLong("dspr_sales_contact"));
    }
}
