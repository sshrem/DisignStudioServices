package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.Design;
import com.disignstudio.project.db.bean.DesignItem;
import com.disignstudio.project.db.mapper.DesignItemRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignItemDaoImpl implements IDesignItemDao {

    private static final String TABLE_NAME = "dsdi_design_items";
    private static final String UPDATE_DESIGN_ITEM_QUERY = "update dsdi_design_items set dsdi_design_id = ? ,dsdi_offering_id = ? ,dsdi_room_id = ? where dsdi_id = ?";
    private static final String FIND_DESIGN_ITEM_BY_DESIGN_QUERY = "select * from dsdi_design_items where dsdi_design_id = ?";
    private static final String FIND_DESIGN_WITH_DATA_ITEM_BY_DESIGN_QUERY = "select * from dsdi_design_items where dsdi_design_id IN (:ids)";

    private JdbcTemplate jdbcTemplate;
    private RowMapper<DesignItem> rowMapper;

    @Inject
    public DesignItemDaoImpl(JdbcTemplate jdbcTemplate, DesignItemRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public long saveOrUpdate(DesignItem item) {

        if (item.getId() > 0L) {
            jdbcTemplate.update(UPDATE_DESIGN_ITEM_QUERY,
                    new Object[]{item.getDesignId(), item.getOfferingId(), item.getRoomId(), item.getId()},
                    new int[]{Types.BIGINT, Types.BIGINT, Types.INTEGER, Types.BIGINT});
            return item.getId();
        } else {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingColumns("dsdi_design_id", "dsdi_offering_id", "dsdi_room_id")
                    .usingGeneratedKeyColumns("dsdi_id");

            Map<String, Object> insertParameters = new HashMap<>();
            insertParameters.put("dsdi_design_id", item.getDesignId());
            insertParameters.put("dsdi_offering_id", item.getOfferingId());
            insertParameters.put("dsdi_room_id", item.getRoomId());
            Number generatedId = insert.executeAndReturnKey(insertParameters);
            return generatedId.longValue();
        }
    }

    @Override
    public void insertBatch(final List<DesignItem> designItems){

        String sql = "INSERT INTO dsdi_design_items (dsdi_design_id, dsdi_offering_id, dsdi_room_id)" +
                " VALUES (:designId, :offeringId, :roomId)";

        List<Map<String, Object>> batchValues = new ArrayList<>(designItems.size());
        for (DesignItem item : designItems) {
            batchValues.add(
                    new MapSqlParameterSource()
                            .addValue("designId", item.getDesignId())
                            .addValue("offeringId", item.getOfferingId())
                            .addValue("roomId", item.getRoomId())
                            .getValues());
        }

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedParameterJdbcTemplate.batchUpdate(sql, batchValues.toArray(new Map[designItems.size()]));
    }



    @Override
    public List<DesignItem> findByDesign(long designId) {
        return jdbcTemplate.query(FIND_DESIGN_ITEM_BY_DESIGN_QUERY,
                new Object[]{designId},
                new int[]{Types.BIGINT}, rowMapper);
    }

    @Override
    public List<DesignItem> findByDesignIds(List<Long> designIds){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", designIds);

        return namedParameterJdbcTemplate.query(FIND_DESIGN_WITH_DATA_ITEM_BY_DESIGN_QUERY, parameters, rowMapper );
    }
}
