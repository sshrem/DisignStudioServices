package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int i) throws SQLException {
        return new Contact(rs.getLong("dsco_id"), rs.getString("dsco_name"), rs.getString("dsco_email"), rs.getString("dsco_phone"));
    }
}
