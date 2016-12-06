package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.Designer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class DesignerRowMapper implements RowMapper<Designer> {

    @Override
    public Designer mapRow(ResultSet rs, int i) throws SQLException {
        return new Designer(rs.getLong("dsde_id"), rs.getLong("dsde_contact_id"), rs.getString("dsde_image"));
    }
}
