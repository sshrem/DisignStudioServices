package com.disignstudio.supplier.db.dao;

import com.disignstudio.supplier.db.bean.SupplierBranch;
import com.disignstudio.supplier.db.mapper.SupplierBranchRSExtractor;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 1/5/16.
 */
public class SupplierBranchDaoImpl implements ISupplierBranchDao {

    private static final String ADD_SUPPLIER_BRANCH_QUERY = "insert into ds_supplier_branches (supplier_branch_supplier_id,supplier_branch_name,supplier_branch_address,supplier_branch_city,supplier_branch_lon,supplier_branch_lat) values (%d,'%s','%s',%d,%f,%f)";
    private static final String UPDATE_SUPPLIER_BRANCH_QUERY = "update ds_supplier_branches set supplier_branch_supplier_id=%d, supplier_branch_name='%s', supplier_branch_address='%s', supplier_branch_city = %d, supplier_branch_lon = %f, supplier_branch_lat = %f where supplier_branch_id = %d";
    private static final String FIND_SUPPLIER_BRANCH_BY_ID_QUERY = "select * from ds_supplier_branches where supplier_branch_id = %d";

    private JdbcTemplate jdbcTemplate;
    private SupplierBranchRSExtractor rse;

    @Inject
    public SupplierBranchDaoImpl(JdbcTemplate jdbcTemplate, SupplierBranchRSExtractor rse) {
        this.jdbcTemplate = jdbcTemplate;
        this.rse = rse;
    }

    @Override
    public void saveOrUpdate(SupplierBranch supplierBranch) {

        String query = (supplierBranch.getId() > 0L) ?
                String.format(UPDATE_SUPPLIER_BRANCH_QUERY, supplierBranch.getSupplierId(), supplierBranch.getName(), supplierBranch.getAddress(), supplierBranch.getCity(), supplierBranch.getLon(), supplierBranch.getLat(), supplierBranch.getId()) :
                String.format(ADD_SUPPLIER_BRANCH_QUERY, supplierBranch.getSupplierId(), supplierBranch.getName(), supplierBranch.getAddress(), supplierBranch.getCity(), supplierBranch.getLon(), supplierBranch.getLat());
        jdbcTemplate.execute(query);
    }

    @Override
    public SupplierBranch findById(long id) {
        String query = String.format(FIND_SUPPLIER_BRANCH_BY_ID_QUERY, id);
        List<SupplierBranch> result = jdbcTemplate.query(query, rse);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
