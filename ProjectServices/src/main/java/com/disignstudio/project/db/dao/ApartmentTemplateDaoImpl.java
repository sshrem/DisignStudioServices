package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.ApartmentTemplate;
import com.disignstudio.project.db.mapper.ApartmentTemplateRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public class ApartmentTemplateDaoImpl implements IApartmentTemplateDao {

    private static final String TABLE_NAME = "dsat_apartment_templates";
    private static final String UPDATE_APARTMENT_TEMPLATE_QUERY = "update dsat_apartment_templates set dsat_project_id=?,dsat_name=?, dsat_image = ?, dsat_num_of_rooms = ?, dsat_default_facebook_video_url = ? where dsat_id = ?";
    private static final String FIND_APARTMENT_TEMPLATE_BY_ID_QUERY = "select * from dsat_apartment_templates where dsat_id = %d";
    private static final String FIND_APARTMENT_TEMPLATE_BY_PROJECT_ID_QUERY = "select * from dsat_apartment_templates where dsat_project_id = %d";
    private static final String FIND_APARTMENT_TEMPLATE_BY_CODE = "select * from dsat_apartment_templates where dsat_code = '%s'";

    private JdbcTemplate jdbcTemplate;
    private ApartmentTemplateRowMapper rowMapper;

    @Inject
    public ApartmentTemplateDaoImpl(JdbcTemplate jdbcTemplate, ApartmentTemplateRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public long saveOrUpdate(ApartmentTemplate aptTmpl) {

        if (aptTmpl.getId() > 0L) {
            jdbcTemplate.update(UPDATE_APARTMENT_TEMPLATE_QUERY,
                    new Object[]{aptTmpl.getProjectId(), aptTmpl.getName(), aptTmpl.getImage(), aptTmpl.getNumOfRooms(), aptTmpl.getId()},
                    new int[]{Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.BIGINT});
            return aptTmpl.getId();
        } else {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingColumns("dsat_code", "dsat_project_id",
                    "dsat_name", "dsat_image", "dsat_num_of_rooms", "dsat_default_facebook_video_url").usingGeneratedKeyColumns("dsat_id");

            Map<String, Object> insertParameters = new HashMap<>();
            insertParameters.put("dsat_code", aptTmpl.getCode());
            insertParameters.put("dsat_project_id", aptTmpl.getProjectId());
            insertParameters.put("dsat_name", aptTmpl.getName());
            insertParameters.put("dsat_image", aptTmpl.getImage());
            insertParameters.put("dsat_num_of_rooms", aptTmpl.getNumOfRooms());
            insertParameters.put("dsat_default_facebook_video_url", aptTmpl.getDefaultFacebookVideoUrl());
            Number generatedId = insert.executeAndReturnKey(insertParameters);
            return generatedId.longValue();
        }
    }

    @Override
    public ApartmentTemplate findById(long id) {
        String query = String.format(FIND_APARTMENT_TEMPLATE_BY_ID_QUERY, id);
        List<ApartmentTemplate> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public List<ApartmentTemplate> findByProject(long projectId) {
        String query = String.format(FIND_APARTMENT_TEMPLATE_BY_PROJECT_ID_QUERY, projectId);
        List<ApartmentTemplate> result = jdbcTemplate.query(query, rowMapper);
        return result;
    }

    @Override
    public ApartmentTemplate findByCode(String code) {
        String query = String.format(FIND_APARTMENT_TEMPLATE_BY_CODE, code);
        List<ApartmentTemplate> result = jdbcTemplate.query(query, rowMapper);
        return result.get(0);
    }
}
