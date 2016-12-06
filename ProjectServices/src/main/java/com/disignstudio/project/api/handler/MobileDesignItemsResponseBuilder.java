package com.disignstudio.project.api.handler;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.api.response.DesignItemSummary;
import com.disignstudio.project.api.response.MobileDesignItemsResponse;
import com.disignstudio.project.cache.DesignsCacheLoader;
import com.disignstudio.project.loader.data.DesignDataWrapper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class MobileDesignItemsResponseBuilder {

    private ICacheClient cacheClient;
    private DesignsCacheLoader designsCacheLoader;
    private CloudinaryUtils cloudinaryUtils;

    @Inject
    public MobileDesignItemsResponseBuilder(ICacheClient cacheClient, DesignsCacheLoader designsCacheLoader, CloudinaryUtils cloudinaryUtils) {
        this.cacheClient = cacheClient;
        this.designsCacheLoader = designsCacheLoader;
        this.cloudinaryUtils = cloudinaryUtils;
    }

    public MobileDesignItemsResponse build(long designId, boolean useCache) {

        /*DesignDataWrapper designItems = cacheClient.getOrLoad(designId, designsCacheLoader, useCache);

        List<DesignItemSummary> responseData = Lists.newArrayList();
        designItems.getDesignItems().forEach(item -> {

            String imgCode = cloudinaryUtils.buildSupplierOfferingPath(item.getSupplierName(), item.getImgCode());
            String supplierName = (StringUtils.isBlank(item.getSupplierDisplayName())) ? item.getSupplierName() : item.getSupplierDisplayName();
            String supplierLogo = cloudinaryUtils.buildSupplierOfferingPath(item.getSupplierName(), item.getSupplierLogo());
            responseData.add(new DesignItemSummary(item.getId(), item.getName(), imgCode, item.getPrice(), supplierName, supplierLogo, item.getSupplierUrl()));
        });*/

        return new MobileDesignItemsResponse(null);
    }
}
