package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.Entreprenuer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class EntrepreneurRowMapper implements RowMapper<Entreprenuer> {

    @Override
    public Entreprenuer mapRow(ResultSet rs, int i) throws SQLException {
        return new Entreprenuer(rs.getLong("dsen_id"), rs.getString("dsen_name"), rs.getString("dsen_address"), rs.getLong("dsen_contact_id"),
                rs.getString("dsen_logo"), rs.getString("dsen_about"));
    }
}
