package com.disignstudio.project.loader;

import com.disignstudio.project.db.bean.DesignItem;
import com.disignstudio.project.db.bean.Supplier;
import com.disignstudio.project.db.bean.SupplierOffering;
import com.disignstudio.project.db.bean.extended.DesignExtended;
import com.disignstudio.project.db.dao.IDesignDao;
import com.disignstudio.project.db.dao.IDesignItemDao;
import com.disignstudio.project.db.dao.ISupplierDao;
import com.disignstudio.project.db.dao.ISupplierOfferingDao;
import com.disignstudio.project.loader.data.DesignData;
import com.disignstudio.project.loader.data.DesignItemData;
import com.disignstudio.project.loader.data.DesignDataWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignsLoader {

    private IDesignItemDao designItemDao;
    private ISupplierDao supplierDao;
    private IDesignDao designDao;
    private ISupplierOfferingDao supplierOfferingDao;

    @Inject
    public DesignsLoader(IDesignItemDao designItemDao, ISupplierDao supplierDao, IDesignDao designDao, ISupplierOfferingDao supplierOfferingDao) {
        this.designItemDao = designItemDao;
        this.supplierDao = supplierDao;
        this.designDao = designDao;
        this.supplierOfferingDao = supplierOfferingDao;
    }

    public DesignDataWrapper load(long apartmentTemplateId) {

        List<DesignData> designs = loadDesigns(apartmentTemplateId);
        return new DesignDataWrapper(apartmentTemplateId, designs);
    }


    private List<DesignData> loadDesigns(long apartmentTemplateId) {

        List<DesignExtended> designs = designDao.findByApartmentTemplateId(apartmentTemplateId);

        List<DesignData> designsData = Lists.newArrayList();
        designs.forEach(design -> {
            List<DesignItemData> designItems = loadDesignItems(design.getId());
            designsData.add(new DesignData(design.getId(), design.getDesignerName(), design.getDesignerImage(), design.getTitle(),
                    design.getImagingCode(), design.getFacebookVideoUrl() , designItems));
        });

        return designsData;
    }

    private List<DesignItemData> loadDesignItems(long designId) {
        List<DesignItem> designItems = designItemDao.findByDesign(designId);

        Map<Long, Supplier> mapSupplierIdToName = Maps.newHashMap();
        List<DesignItemData> designItemData = Lists.newArrayList();

        for (DesignItem item : designItems) {

            SupplierOffering supplierOffering = supplierOfferingDao.findById(item.getOfferingId());
            Supplier supplier = mapSupplierIdToName.get(supplierOffering.getSupplierId());
            if (supplier == null) {
                supplier = supplierDao.findById(supplierOffering.getSupplierId());
                mapSupplierIdToName.put(supplier.getId(), supplier);
            }

            designItemData.add(new DesignItemData(item.getId(), supplierOffering.getName(), supplierOffering.getImageCode(), supplierOffering.getCategory(),
                    supplierOffering.getPrice(), supplier.getName(), supplier.getLogo(), supplier.getUrl(), supplier.getId(), supplier.getDisplayName(),
                    supplierOffering.isStandard(), item.getRoomId(), supplierOffering.getId()));
        }
        return designItemData;
    }
}
