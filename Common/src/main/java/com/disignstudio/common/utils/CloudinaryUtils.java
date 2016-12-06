package com.disignstudio.common.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.Url;
import com.cloudinary.utils.ObjectUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

import java.util.Map;

/**
 * Created by ohadbenporat on 2/26/16.
 */
public class CloudinaryUtils {

    private static final String CLOUDINARY_SEPARATOR = "/";
    private static final String CLOUDINARY_PROJECT_PREFIX = "projects";
    private static final String CLOUDINARY_ENTREPRENEUR_PREFIX = "entrepreneurs";
    private static final String CLOUDINARY_DESIGNER_PREFIX = "designers";
    private static final String CLOUDINARY_SUPPLIER_PREFIX = "suppliers";

    private Cloudinary cloudinary;

    @Inject
    public CloudinaryUtils(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String buildProjectImagesPath(String projectCode, String imgName) {

        return buildPath(CLOUDINARY_PROJECT_PREFIX, projectCode, imgName);
    }

    public String buildApartmentTemplateImagesPath(String projetCode, String apartmentTemplateCode, String imgName) {

        return buildPath(CLOUDINARY_PROJECT_PREFIX, projetCode, apartmentTemplateCode, imgName);
    }

    public String buildEntreprenuerImagesPath(String imgName) {

        return buildPath(CLOUDINARY_ENTREPRENEUR_PREFIX, imgName);
    }

    public String buildDesignerImagesPath(String imgName) {

        return buildPath(CLOUDINARY_DESIGNER_PREFIX, imgName);
    }

    public String buildSupplierOfferingPath(String supplierName, String imgName) {

        return buildPath(CLOUDINARY_SUPPLIER_PREFIX, supplierName, imgName);
    }

    public String generateUrl(String source, boolean isVideo) {

        if (isVideo) {
            return cloudinary.url().resourceType("video").generate(source);
        } else {
            return cloudinary.url().resourceType("image").generate(source);
        }


    }

    public void delete(String prefix,boolean isVideo) throws Exception {
        Map options = Maps.newHashMap();
        if (isVideo){
            options.put("resource_type", "video");
        }

        cloudinary.api().deleteResourcesByPrefix(prefix, options);
    }

    private String buildPath(String first, String second, String... rest) {
        return Joiner.on(CLOUDINARY_SEPARATOR).join(first, second, rest);
    }
}
