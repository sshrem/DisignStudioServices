package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Contact;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public interface IContactDao {

    public void saveOrUpdate(Contact contact);

    public Contact findById(long id);
}
