package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.ApartmentTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public class ApartmentTemplateRowMapper implements RowMapper<ApartmentTemplate> {

    @Override
    public ApartmentTemplate mapRow(ResultSet rs, int i) throws SQLException {
        return new ApartmentTemplate(rs.getLong("dsat_id"), rs.getString("dsat_code"), rs.getLong("dsat_project_id"),
                rs.getString("dsat_name"), rs.getString("dsat_image"), rs.getInt("dsat_num_of_rooms"));
    }
}
