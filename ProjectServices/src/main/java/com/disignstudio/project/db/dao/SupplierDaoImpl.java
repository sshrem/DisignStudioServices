package com.disignstudio.project.db.dao;


import com.disignstudio.project.db.bean.Supplier;
import com.disignstudio.project.db.mapper.SupplierRowMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 1/5/16.
 */
public class SupplierDaoImpl implements ISupplierDao {

    private static final String TABLE_NAME = "dssu_suppliers";
    private static final String UPDATE_SUPPLIER_QUERY = "update dssu_suppliers set dssu_name=?,dssu_display_name=?, dssu_address=?, dssu_contact_id = ? , dssu_country_id = ?, dssu_logo = ?, dssu_url = ? where dssu_id = ?";
    private static final String FIND_SUPPLIER_BY_ID_QUERY = "select * from dssu_suppliers where dssu_id = %d";

    private JdbcTemplate jdbcTemplate;
    private SupplierRowMapper rowMapper;

    @Inject
    public SupplierDaoImpl(JdbcTemplate jdbcTemplate, SupplierRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public long saveOrUpdate(Supplier supplier) {

        if (supplier.getId() > 0L) {
            jdbcTemplate.update(UPDATE_SUPPLIER_QUERY,
                    new Object[]{supplier.getName(), supplier.getDisplayName(), supplier.getAddress(), supplier.getContactId(), supplier.getCountryId(), supplier.getLogo(), supplier.getUrl(), supplier.getId()},
                    new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
            return supplier.getId();
        } else {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_NAME).usingColumns("dssu_name", "dssu_display_name", "dssu_address",
                    "dssu_contact_id", "dssu_country_id", "dssu_logo", "dssu_url").usingGeneratedKeyColumns("dssu_id");

            Map<String, Object> insertParameters = new HashMap<>();
            insertParameters.put("dssu_name", supplier.getName());
            insertParameters.put("dssu_display_name", supplier.getDisplayName());
            insertParameters.put("dssu_address", supplier.getAddress());
            insertParameters.put("dssu_contact_id", supplier.getContactId());
            insertParameters.put("dssu_country_id", supplier.getCountryId());
            insertParameters.put("dssu_logo", supplier.getLogo());
            insertParameters.put("dssu_url", supplier.getUrl());
            Number generatedId = insert.executeAndReturnKey(insertParameters);
            return generatedId.longValue();
        }
    }

    @Override
    public Supplier findById(long id) {
        String query = String.format(FIND_SUPPLIER_BY_ID_QUERY, id);
        List<Supplier> result = jdbcTemplate.query(query, rowMapper);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
