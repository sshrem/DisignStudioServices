package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int i) throws SQLException {
        return new Project(rs.getLong("dspr_id"), rs.getString("dspr_code"), rs.getLong("dspr_entrepreneur_id"), rs.getString("dspr_name"), rs.getString("dspr_address"),
                rs.getInt("dspr_city"), rs.getInt("dspr_country"), rs.getDouble("dspr_lon"), rs.getDouble("dspr_lat"), rs.getBoolean("dspr_is_active"), rs.getString("dspr_logo"), rs.getString("dspr_about"), rs.getLong("dspr_sales_contact"));
    }
}