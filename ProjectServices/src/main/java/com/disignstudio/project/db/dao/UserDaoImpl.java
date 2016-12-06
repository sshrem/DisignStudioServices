package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.User;
import com.disignstudio.project.db.mapper.UserRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Types;
import java.util.List;

/**
 * Created by ohadbenporat on 2/15/16.
 */
public class UserDaoImpl implements IUserDao {

    private static final String ADD_USER_QUERY = "insert into dsus_users (dsus_email,dsus_name,dsus_login_method) values (?,?,?)";
    private static final String UPDATE_USER_QUERY = "update dsus_users set dsus_name = ? ,dsus_login_method = ? where dsus_email = ?";
    private static final String FIND_USER_BY_EMAIL_QUERY = "select * from dsus_users where dsus_email = ?";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> rowMapper;

    @Inject
    public UserDaoImpl(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public long saveOrUpdate(User user) {

        User userFromDb = findByEmail(user.getEmail());

        if (userFromDb == null) {
            jdbcTemplate.update(ADD_USER_QUERY,
                    new Object[]{user.getEmail(), user.getName(), user.getLoginMethod()},
                    new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER});
            userFromDb = findByEmail(user.getEmail());
        } else {
            jdbcTemplate.update(UPDATE_USER_QUERY,
                    new Object[]{user.getName(), user.getLoginMethod(), user.getEmail()},
                    new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
        }

        return userFromDb.getUserId();
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = jdbcTemplate.query(FIND_USER_BY_EMAIL_QUERY,
                new Object[]{email},
                new int[]{Types.VARCHAR}, rowMapper);
        return (users.isEmpty()) ? null : users.get(0);
    }
}
