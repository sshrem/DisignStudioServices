package com.disignstudio.project.db.mapper;

import com.disignstudio.project.db.bean.User;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 2/15/16.
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        return new User(rs.getLong("dsus_id"), rs.getString("dsus_email"), rs.getString("dsus_name"), rs.getInt("dsus_login_method"),
                new DateTime(rs.getDate("dsus_last_login")));
    }
}
