package com.disignstudio.project.api;

import com.disignstudio.common.error.InvalidRequestException;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.admin.DesignsUploader;
import com.disignstudio.project.admin.OfferingsUploader;
import com.disignstudio.web.response.DesignStudioResponseBuilder;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ohadbenporat on 4/10/16.
 */
@Path("/api/admin/")
@Produces(MediaType.APPLICATION_JSON)
public class AdminAPI {

    private DesignsUploader designsUploader;
    private OfferingsUploader offeringsUploader;
    private DesignStudioResponseBuilder responseBuilder;
    private CloudinaryUtils cloudinaryUtils;

    @Inject
    public AdminAPI(DesignsUploader designsUploader, OfferingsUploader offeringsUploader, DesignStudioResponseBuilder responseBuilder, CloudinaryUtils cloudinaryUtils) {
        this.designsUploader = designsUploader;
        this.offeringsUploader = offeringsUploader;
        this.responseBuilder = responseBuilder;
        this.cloudinaryUtils = cloudinaryUtils;
    }

    @GET
    @Path("/deleteMedia")
    public String delete(@QueryParam("prefix") String prefix, @QueryParam("isVideo") boolean isVideo) throws Exception {
        cloudinaryUtils.delete(prefix, isVideo);
        return "Finished";
    }

    @GET
    @Path("/uploadDesigns")
    public Response uploadDesigns() {

        try {
            designsUploader.upload();
            return responseBuilder.build(null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseBuilder.error();
        }
    }

    @GET
    @Path("/uploadOfferings")
    public Response uploadOfferings() {

        try {
            offeringsUploader.upload();
            return responseBuilder.build(null);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }
}
