package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Designer;
import com.disignstudio.project.db.mapper.DesignerRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class DesignerDaoImpl implements IDesignerDao {

    private static final String ADD_DESIGNER_QUERY = "insert into dsde_designers (dsde_contact_id,dsde_image) values(%d,'%s')";
    private static final String UPDATE_DESIGNER_QUERY = "update dsde_designers set dsde_contact_id=%d,dsde_image = '%s' where dsde_id = %d";
    private static final String FIND_DESIGNER_BY_ID_QUERY = "select * from dsde_designers where dsde_id = %d";

    private JdbcTemplate jdbcTemplate;
    private DesignerRowMapper rowMapper;

    @Inject
    public DesignerDaoImpl(JdbcTemplate jdbcTemplate, DesignerRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void saveOrUpdate(Designer designer) {

        String query = (designer.getId() > 0L) ?
                String.format(UPDATE_DESIGNER_QUERY, designer.getContactId(), designer.getImage(), designer.getId()) :
                String.format(ADD_DESIGNER_QUERY, designer.getContactId(), designer.getImage());
        jdbcTemplate.execute(query);
    }

    @Override
    public Designer findById(long id) {
        String query = String.format(FIND_DESIGNER_BY_ID_QUERY, id);
        List<Designer> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
