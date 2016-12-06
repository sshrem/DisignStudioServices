package com.disignstudio.supplier.db.dao;

import com.disignstudio.supplier.db.bean.SupplierGroup;
import com.disignstudio.supplier.db.mapper.SupplierGroupRSExtractor;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierGroupDaoImpl implements ISupplierGroupDao {

    private static final String ADD_SUPPLIER_GROUP_QUERY = "insert into ds_supplier_groups (supplier_group_name) values ('%s')";
    private static final String UPDATE_SUPPLIER_GROUP_QUERY = "update ds_supplier_groups set supplier_group_name='%s' where supplier_group_id = %d";
    private static final String FIND_SUPPLIER_GROUP_BY_ID_QUERY = "select * from ds_supplier_groups where supplier_group_id = %d";

    private JdbcTemplate jdbcTemplate;
    private SupplierGroupRSExtractor rse;

    @Inject
    public SupplierGroupDaoImpl(JdbcTemplate jdbcTemplate, SupplierGroupRSExtractor rse) {
        this.jdbcTemplate = jdbcTemplate;
        this.rse = rse;
    }

    @Override
    public void saveOrUpdate(SupplierGroup supplierGroup) {

        String query = (supplierGroup.getId() > 0L) ?
                String.format(UPDATE_SUPPLIER_GROUP_QUERY, supplierGroup.getName(), supplierGroup.getId()) :
                String.format(ADD_SUPPLIER_GROUP_QUERY, supplierGroup.getName());
        jdbcTemplate.execute(query);
    }

    @Override
    public SupplierGroup findById(long id) {
        String query = String.format(FIND_SUPPLIER_GROUP_BY_ID_QUERY, id);
        List<SupplierGroup> result = jdbcTemplate.query(query, rse);
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }
}
