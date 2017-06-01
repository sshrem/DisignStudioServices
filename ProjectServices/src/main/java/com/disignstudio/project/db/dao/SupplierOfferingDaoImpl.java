package com.disignstudio.project.db.dao;


import com.disignstudio.project.db.bean.SupplierOffering;
import com.disignstudio.project.db.mapper.SupplierOfferingRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierOfferingDaoImpl implements ISupplierOfferingDao {

    private static final String TABLE_NAME = "dsso_supplier_offerings";
    private static final String UPDATE_SUPPLIER_OFFERING_QUERY = "update dsso_supplier_offerings set dsso_supplier_id= ? ,dsso_name=?,dsso_description=?,dsso_in_stock=?, dsso_image_code=?, dsso_price = ? where dsso_id = ?";
    private static final String FIND_SUPPLIER_OFFERING_BY_ID_QUERY = "select * from dsso_supplier_offerings where dsso_id = %d";
    private static final String FIND_SUPPLIER_OFFERINGS_BY_IDS_QUERY = "select * from dsso_supplier_offerings where dsso_id IN (:ids)";

    private JdbcTemplate jdbcTemplate;
    private SupplierOfferingRowMapper rowMapper;

    @Inject
    public SupplierOfferingDaoImpl(JdbcTemplate jdbcTemplate, SupplierOfferingRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public long saveOrUpdate(SupplierOffering offering) {

        if (offering.getId() > 0L) {
            jdbcTemplate.update(UPDATE_SUPPLIER_OFFERING_QUERY,
                    new Object[]{offering.getSupplierId(), offering.getName(), offering.getDescription(), offering.isInStock(), offering.getImageCode(), offering.getPrice(), offering.getId()},
                    new int[]{Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.BOOLEAN, Types.VARCHAR, Types.DOUBLE, Types.BIGINT});
            return offering.getId();
        } else {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingColumns("dsso_supplier_id", "dsso_name",
                    "dsso_description", "dsso_in_stock", "dsso_image_code", "dsso_price", "dsso_category")
                    .usingGeneratedKeyColumns("dsso_id");

            Map<String, Object> insertParameters = new HashMap<>();
            insertParameters.put("dsso_supplier_id", offering.getSupplierId());
            insertParameters.put("dsso_name", offering.getName());
            insertParameters.put("dsso_description", offering.getDescription());
            insertParameters.put("dsso_in_stock", offering.isInStock());
            insertParameters.put("dsso_image_code", offering.getImageCode());
            insertParameters.put("dsso_price", offering.getPrice());
            insertParameters.put("dsso_category", offering.getCategory());
            Number generatedId = insert.executeAndReturnKey(insertParameters);
            return generatedId.longValue();
        }
    }

    @Override
    public SupplierOffering findById(long id) {
        String query = String.format(FIND_SUPPLIER_OFFERING_BY_ID_QUERY, id);
        List<SupplierOffering> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public List<SupplierOffering> findByIds(List<Long> offeringIds){
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", offeringIds);

        return namedParameterJdbcTemplate.query(FIND_SUPPLIER_OFFERINGS_BY_IDS_QUERY, parameters, rowMapper );
    }

}
