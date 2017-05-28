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
import com.disignstudio.project.loader.data.DesignDataWrapper;
import com.disignstudio.project.loader.data.DesignItemData;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        Map<Long, DesignExtended> idsToDesignExtended = designs.stream().collect(Collectors.toMap(x -> x.getId(), x -> x));
        Multimap<Long, DesignItemData> designItemsMap = loadDesignItems(idsToDesignExtended.keySet().stream().collect(Collectors.toList()));

        Map<Long, Collection<DesignItemData>> designsMap = designItemsMap.asMap();
        for(Long designId : designsMap.keySet()){
            List<DesignItemData> designItems = designsMap.get(designId).stream().collect(Collectors.toList());
            DesignExtended design = idsToDesignExtended.get(designId);
            designsData.add(new DesignData(design.getId(), design.getDesignerName(), design.getDesignerImage(), design.getTitle(),
                    design.getImagingCode(), design.getFacebookVideoUrl() , designItems));
        }

        return designsData;
    }

    private Multimap<Long, DesignItemData> loadDesignItems(List<Long> designIds) {
        List<DesignItem> designItems = designItemDao.findByDesignIds(designIds);

        Map<Long, Supplier> mapSupplierIdToName = Maps.newHashMap();
        Map<Long, SupplierOffering> idToSupplierOffering = Maps.newHashMap();
        Multimap<Long, DesignItemData> designItemsMap = ArrayListMultimap.create();

        for (DesignItem item : designItems) {


            SupplierOffering supplierOffering = idToSupplierOffering.get(item.getOfferingId());
            if (supplierOffering == null){
                supplierOffering = supplierOfferingDao.findById(item.getOfferingId());
                idToSupplierOffering.put(item.getOfferingId(), supplierOffering);
            }
            Supplier supplier = mapSupplierIdToName.get(supplierOffering.getSupplierId());
            if (supplier == null) {
                supplier = supplierDao.findById(supplierOffering.getSupplierId());
                mapSupplierIdToName.put(supplier.getId(), supplier);
            }

            DesignItemData data = new DesignItemData(item.getId(), supplierOffering.getName(), supplierOffering.getImageCode(), supplierOffering.getCategory(),
                    supplierOffering.getPrice(), supplier.getName(), supplier.getLogo(), supplier.getUrl(), supplier.getId(), supplier.getDisplayName(),
                    supplierOffering.isStandard(), item.getRoomId(), supplierOffering.getId());
            designItemsMap.put(item.getDesignId(), data);
        }
        return designItemsMap;
    }
}
