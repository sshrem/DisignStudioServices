package com.disignstudio.supplier.db.mapper;

import com.disignstudio.supplier.db.bean.SupplierBranch;
import com.google.common.collect.Lists;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class SupplierBranchRSExtractor implements ResultSetExtractor<List<SupplierBranch>> {

    @Override
    public List<SupplierBranch> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<SupplierBranch> result = Lists.newArrayList();
        while (rs.next()) {
            result.add(new SupplierBranch(rs.getLong("supplier_branch_id"), rs.getLong("supplier_branch_supplier_id"), rs.getString("supplier_branch_name"),
                    rs.getString("supplier_branch_address"), rs.getInt("supplier_branch_city"),rs.getDouble("supplier_branch_lon"),rs.getDouble("supplier_branch_lat")));
        }
        return result;
    }
}
