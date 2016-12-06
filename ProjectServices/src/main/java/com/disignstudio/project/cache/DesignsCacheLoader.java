package com.disignstudio.project.cache;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.common.cache.ICacheLoader;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.cache.pojo.DesignCachedData;
import com.disignstudio.project.cache.pojo.DesignItemCachedData;
import com.disignstudio.project.cache.pojo.DesignsCachedData;
import com.disignstudio.project.loader.DesignsLoader;
import com.disignstudio.project.loader.data.DesignDataWrapper;
import com.disignstudio.project.loader.data.DesignItemData;
import com.disignstudio.project.redirect.RedirectUrlBuilder;
import com.disignstudio.project.stats.EViewImagingSource;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignsCacheLoader implements ICacheLoader {

    private final static String CACHE_PREFIX_KEY = "DESIGNS_CACHE_KEY";
    private final int cacheExpiration;
    private DesignsLoader loader;
    private CloudinaryUtils cloudinaryUtils;

    @Inject
    public DesignsCacheLoader(DesignsLoader loader, @Named("designsDataCacheExpiration") int cacheExpiration, CloudinaryUtils cloudinaryUtils) {
        this.cacheExpiration = cacheExpiration;
        this.loader = loader;
        this.cloudinaryUtils = cloudinaryUtils;
    }

    @Override
    public DesignsCachedData load(Object key) {

        DesignDataWrapper designsData = loader.load((Long) key);

        List<DesignCachedData> designs = Lists.newArrayList();
        designsData.getDesigns().forEach(design -> {


            String designerLogo = cloudinaryUtils.buildDesignerImagesPath(design.getDesignerImage());


            List<DesignItemCachedData> designItems = buildDesignItems(design.getDesignItems());
            designs.add(new DesignCachedData(design.getId(), design.getTitle(), design.getDesignerName(), designerLogo, design.getImagingCode(), designItems));
        });

        return new DesignsCachedData(designs);
    }

    private List<DesignItemCachedData> buildDesignItems(List<DesignItemData> designItems) {

        List<DesignItemCachedData> items = Lists.newArrayList();
        designItems.forEach(item -> {

            String supplierLogo = cloudinaryUtils.buildSupplierOfferingPath(item.getSupplierName(), item.getSupplierLogo());
            String supplierName = (StringUtils.isBlank(item.getSupplierDisplayName())) ? item.getSupplierName() : item.getSupplierDisplayName();
            String imgCode = cloudinaryUtils.buildSupplierOfferingPath(item.getSupplierName(), item.getImgCode());
            items.add(new DesignItemCachedData(item.getId(), item.getCategory(), item.getName(), item.getSupplierId(), item.isStandard(), supplierName, supplierLogo,
                    item.getSupplierUrl(), item.getRoomId(), imgCode,item.getOfferingId()));
        });
        return items;
    }

    @Override
    public int getCacheExpiration() {
        return cacheExpiration;
    }

    @Override
    public TypeToken<DesignsCachedData> getCacheValueType() {
        return TypeToken.of(DesignsCachedData.class);
    }

    @Override
    public EntityCacheKey<Long> generateKey(Object keyEntity) {
        return new EntityCacheKey<>((Long) keyEntity, CACHE_PREFIX_KEY);
    }
}
