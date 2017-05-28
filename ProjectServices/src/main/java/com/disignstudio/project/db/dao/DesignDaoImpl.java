package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Design;
import com.disignstudio.project.db.bean.extended.DesignExtended;
import com.disignstudio.project.db.mapper.DesignExtendedRowMapper;
import com.disignstudio.project.db.mapper.DesignRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class DesignDaoImpl implements IDesignDao {

    private static final String TABLE_NAME = "dsds_designs";
    private static final String UPDATE_DESIGN_QUERY = "update dsds_designs set dsds_designer_id = ?, dsds_apartment_template_id = ?, dsds_imaging_code = ?,dsds_title = ?, dsds_facebook_video_url = ? where dsds_id = ?";
    private static final String FIND_DESIGN_BY_ID_QUERY = "select * from dsds_designs where dsds_id = %d";
    private static final String FIND_DESIGN_BY_TEMPLATE_ID_QUERY = "select a.*,b.dsde_image,c.dsco_name " +
            "from dsds_designs a join dsde_designers b on dsds_designer_id = dsde_id join dsco_contacts c on dsde_contact_id = dsco_id " +
            "where dsds_apartment_template_id = %d;";


    private JdbcTemplate jdbcTemplate;
    private DesignExtendedRowMapper deRowMapper;
    private DesignRowMapper designRowMapper;

    @Inject
    public DesignDaoImpl(JdbcTemplate jdbcTemplate, DesignExtendedRowMapper deRowMapper, DesignRowMapper designRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.deRowMapper = deRowMapper;
        this.designRowMapper = designRowMapper;
    }

    @Override
    public long saveOrUpdate(Design design) {

        if (design.getId() > 0L) {
            jdbcTemplate.update(UPDATE_DESIGN_QUERY,
                    new Object[]{design.getDesignerId(), design.getApartmentTemplateId(), design.getImagingCode(), design.getTitle(), design.getId()},
                    new int[]{Types.BIGINT, Types.INTEGER, Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.BIGINT});
            return design.getId();
        } else {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingColumns("dsds_designer_id",
                    "dsds_apartment_template_id", "dsds_imaging_code", "dsds_title", "dsds_facebook_video_url").usingGeneratedKeyColumns("dsds_id");
            Map<String, Object> insertParameters = new HashMap<>();
            insertParameters.put("dsds_designer_id", design.getDesignerId());
            insertParameters.put("dsds_apartment_template_id", design.getApartmentTemplateId());
            insertParameters.put("dsds_imaging_code", design.getImagingCode());
            insertParameters.put("dsds_title", design.getTitle());
            insertParameters.put("dsds_facebook_video_url", design.getFacebookVideoUrl());
            Number generatedId = insert.executeAndReturnKey(insertParameters);
            return generatedId.longValue();
        }
    }

    @Override
    public Design findById(long id) {
        String query = String.format(FIND_DESIGN_BY_ID_QUERY, id);
        List<Design> result = jdbcTemplate.query(query, designRowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public List<DesignExtended> findByApartmentTemplateId(long roomId) {
        String query = String.format(FIND_DESIGN_BY_TEMPLATE_ID_QUERY, roomId);
        List<DesignExtended> result = jdbcTemplate.query(query, deRowMapper);
        return result;
    }
}
