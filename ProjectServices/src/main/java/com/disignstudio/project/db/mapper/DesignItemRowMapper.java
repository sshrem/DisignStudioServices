package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.DesignItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignItemRowMapper implements RowMapper<DesignItem> {

    @Override
    public DesignItem mapRow(ResultSet rs, int i) throws SQLException {
        return new DesignItem(rs.getLong("dsdi_id"), rs.getLong("dsdi_design_id"), rs.getLong("dsdi_offering_id"), rs.getInt("dsdi_room_id"));
    }
}
