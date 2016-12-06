package com.disignstudio.project.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ohadbenporat on 4/11/16.
 */
public class CloudinaryUploader {

    private Cloudinary cloudinary;

    @Inject
    public CloudinaryUploader(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public void upload(String publicId, File file, boolean isVideo) throws IOException {
        Map params = ObjectUtils.asMap("public_id", publicId);

        if (isVideo) {
            cloudinary.uploader().uploadLarge(file, params);
        } else {
            cloudinary.uploader().upload(file, params);
        }
    }
}
