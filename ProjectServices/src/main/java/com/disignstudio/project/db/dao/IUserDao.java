package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.User;

/**
 * Created by ohadbenporat on 2/15/16.
 */
public interface IUserDao {

    public long saveOrUpdate(User user);

    public User findByEmail(String email);
}
