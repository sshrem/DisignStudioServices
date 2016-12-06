package com.disignstudio.project.api.handler;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.api.request.DesignSuppliersRequest;
import com.disignstudio.project.api.response.MobileDesignSuppliersResponse;
import com.disignstudio.project.api.response.SupplierSummary;
import com.disignstudio.project.cache.DesignsCacheLoader;
import com.disignstudio.project.cache.pojo.DesignCachedData;
import com.disignstudio.project.cache.pojo.DesignsCachedData;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ohadbenporat on 4/14/16.
 */
public class MobileDesignSuppliersRequestHandler {

    private ICacheClient cacheClient;
    private DesignsCacheLoader designsCacheLoader;

    @Inject
    public MobileDesignSuppliersRequestHandler(ICacheClient cacheClient, DesignsCacheLoader designsCacheLoader) {
        this.cacheClient = cacheClient;
        this.designsCacheLoader = designsCacheLoader;
    }

    public MobileDesignSuppliersResponse build(DesignSuppliersRequest request) {

        DesignsCachedData allDesigns = cacheClient.getOrLoad(request.getAtId(), designsCacheLoader, request.isUseCache());
        DesignCachedData requiredDesign = findDesign(allDesigns.getDesigns(), request.getdId());

        Set<SupplierSummary> summary = Sets.newHashSet();

        requiredDesign.getItems().forEach(item -> {
            summary.add(new SupplierSummary(item.getSupplierId(), item.getSupplierName(), item.getSupplierLogo(), item.getSupplierUrl()));
        });

        return new MobileDesignSuppliersResponse(summary);
    }

    private DesignCachedData findDesign(List<DesignCachedData> designs, long designId) {
        for (DesignCachedData obj : designs) {
            if (obj.getId() == designId) {
                return obj;
            }
        }
        return null;
    }
}
