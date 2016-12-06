package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.Supplier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class SupplierRowMapper implements RowMapper<Supplier> {

    @Override
    public Supplier mapRow(ResultSet rs, int i) throws SQLException {
        return new Supplier(rs.getLong("dssu_id"), rs.getString("dssu_name"), rs.getString("dssu_display_name"), rs.getString("dssu_address"),
                rs.getInt("dssu_country_id"), rs.getLong("dssu_contact_id"), rs.getString("dssu_logo"), rs.getString("dssu_url"));
    }
}
