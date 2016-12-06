package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Entreprenuer;
import com.disignstudio.project.db.mapper.EntrepreneurRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 1/5/16.
 */
public class EntrepreneurDaoImpl implements IEntrepreneurDao {

    private static final String ADD_ENTREPRENEUR_QUERY = "insert into dsen_entrepreneurs (dsen_about,dsen_name,dsen_address,dsen_contact_id) values('%s','%s','%s',%d)";
    private static final String UPDATE_ENTREPRENEUR_QUERY = "update dsen_entrepreneurs set dsen_about='%s', dsen_name='%s',dsen_address='%s',dsen_contact_id=%d where dsen_id = %d";
    private static final String FIND_ENTREPRENEUR_BY_ID_QUERY = "select * from dsen_entrepreneurs where dsen_id = %d";

    private JdbcTemplate jdbcTemplate;
    private EntrepreneurRowMapper rowMapper;

    @Inject
    public EntrepreneurDaoImpl(JdbcTemplate jdbcTemplate, EntrepreneurRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void saveOrUpdate(Entreprenuer entreprenuer) {

        String query = (entreprenuer.getId() > 0L) ?
                String.format(UPDATE_ENTREPRENEUR_QUERY, entreprenuer.getAbout(), entreprenuer.getName(), entreprenuer.getAddress(), entreprenuer.getContactId(), entreprenuer.getId()) :
                String.format(ADD_ENTREPRENEUR_QUERY, entreprenuer.getAbout(), entreprenuer.getName(), entreprenuer.getAddress(), entreprenuer.getContactId());
        jdbcTemplate.execute(query);
    }

    @Override
    public Entreprenuer findById(long id) {
        String query = String.format(FIND_ENTREPRENEUR_BY_ID_QUERY, id);
        List<Entreprenuer> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
