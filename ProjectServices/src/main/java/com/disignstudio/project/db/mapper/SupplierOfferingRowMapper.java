package com.disignstudio.project.db.mapper;


import com.disignstudio.project.db.bean.SupplierOffering;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class SupplierOfferingRowMapper implements RowMapper<SupplierOffering> {

    @Override
    public SupplierOffering mapRow(ResultSet rs, int i) throws SQLException {
        return new SupplierOffering(rs.getLong("dsso_id"), rs.getLong("dsso_supplier_id"), rs.getInt("dsso_category"),
                rs.getString("dsso_name"), rs.getString("dsso_description"), rs.getBoolean("dsso_is_standard"), rs.getBoolean("dsso_in_stock"), rs.getString("dsso_image_code"),
                rs.getDouble("dsso_price"));
    }
}
