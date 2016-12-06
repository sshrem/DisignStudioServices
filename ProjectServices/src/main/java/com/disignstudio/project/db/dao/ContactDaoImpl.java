package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Contact;
import com.disignstudio.project.db.mapper.ContactRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public class ContactDaoImpl implements IContactDao {

    private static final String ADD_CONTACT_QUERY = "insert into dsco_contacts (dsco_name,dsco_email,dsco_phone) values('%s','%s','%s')";
    private static final String UPDATE_CONTACT_QUERY = "update dsco_contacts set dsco_name = '%s',dsco_email = '%s', dsco_phone='%s' where dsco_id = %d";
    private static final String FIND_CONTACT_BY_ID_QUERY = "select * from dsco_contacts  where dsco_id = %d";

    private JdbcTemplate jdbcTemplate;
    private ContactRowMapper rowMapper;

    @Inject
    public ContactDaoImpl(JdbcTemplate jdbcTemplate, ContactRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void saveOrUpdate(Contact contact) {

        String query = (contact.getId() > 0L) ?
                String.format(UPDATE_CONTACT_QUERY, contact.getName(), contact.getEmail(), contact.getPhone(), contact.getId()) :
                String.format(ADD_CONTACT_QUERY, contact.getName(), contact.getEmail(), contact.getPhone());
        jdbcTemplate.execute(query);
    }

    @Override
    public Contact findById(long id) {
        String query = String.format(FIND_CONTACT_BY_ID_QUERY, id);
        List<Contact> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
