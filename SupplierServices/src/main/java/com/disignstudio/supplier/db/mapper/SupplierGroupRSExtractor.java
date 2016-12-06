package com.disignstudio.supplier.db.mapper;

import com.disignstudio.supplier.db.bean.SupplierGroup;
import com.google.common.collect.Lists;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierGroupRSExtractor implements ResultSetExtractor<List<SupplierGroup>> {

    @Override
    public List<SupplierGroup> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<SupplierGroup> result = Lists.newArrayList();
        while (rs.next()) {
            result.add(new SupplierGroup(rs.getLong("supplier_group_id"), rs.getString("supplier_group_name")));
        }
        return result;
    }
}
