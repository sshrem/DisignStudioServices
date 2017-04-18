package com.disignstudio.project.api;

import com.codahale.metrics.annotation.Timed;
import com.disignstudio.common.error.InvalidRequestException;
import com.disignstudio.project.api.handler.MobileDesignSuppliersRequestHandler;
import com.disignstudio.project.api.handler.MobileDesignsFiltersRequestHandler;
import com.disignstudio.project.api.handler.MobileDesignsRequestHandler;
import com.disignstudio.project.api.handler.MobileProjectRequestHandler;
import com.disignstudio.project.api.request.DesignSuppliersRequest;
import com.disignstudio.project.api.request.DesignsFiltersRequest;
import com.disignstudio.project.api.request.DesignsRequest;
import com.disignstudio.project.api.request.ProjectRequest;
import com.disignstudio.project.api.response.MobileDesignFiltersResponse;
import com.disignstudio.project.api.response.MobileDesignSuppliersResponse;
import com.disignstudio.project.api.response.MobileDesignsResponse;
import com.disignstudio.project.api.response.MobileProjectResponse;
import com.disignstudio.web.response.DesignStudioResponseBuilder;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ohadbenporat on 2/2/16.
 */
@Path("/api/mobile/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MobileContentAPI {

    private MobileProjectRequestHandler projectResponseBuilder;
    private MobileDesignsRequestHandler designsResponseBuilder;
    private MobileDesignsFiltersRequestHandler designsFiltersRequestHandler;
    private MobileDesignSuppliersRequestHandler designSuppliersResponseBuilder;
    private DesignStudioResponseBuilder responseBuilder;

    @Inject
    public MobileContentAPI(MobileProjectRequestHandler projectResponseBuilder, MobileDesignSuppliersRequestHandler designSuppliersResponseBuilder,
                            MobileDesignsFiltersRequestHandler designsFiltersRequestHandler, MobileDesignsRequestHandler designsResponseBuilder) {

        this.projectResponseBuilder = projectResponseBuilder;
        this.designsResponseBuilder = designsResponseBuilder;
        this.designSuppliersResponseBuilder = designSuppliersResponseBuilder;
        this.designsFiltersRequestHandler = designsFiltersRequestHandler;
        this.responseBuilder = new DesignStudioResponseBuilder();
    }

    @POST
    @Path("/project")
    @Timed(name = "mobileProjectRequests", absolute = true)
    public Response projectInfo(ProjectRequest request) {

        try {
            MobileProjectResponse data = projectResponseBuilder.build(request);
            return responseBuilder.build(data);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/designs")
    @Timed(name = "mobileDesignsRequests", absolute = true)
    public Response designsInfo(DesignsRequest request) {
        try {
            MobileDesignsResponse data = designsResponseBuilder.build(request);
            return responseBuilder.build(data);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    /*
    @POST
    @Path("/designItems")
    @Timed(name = "mobileDesignItemsRequests", absolute = true)
    public Response designsItems(@QueryParam("dId") long designId, @QueryParam("useCache") @DefaultValue("true") boolean useCache) {

        try {
            MobileDesignItemsResponse responseData = designItemsResponseBuilder.build(designId, useCache);
            return responseBuilder.build(responseData);
        } catch (Exception e) {
            throw new InvalidRequestException(e.getMessage(), e);
        }
    }*/

    @POST
    @Path("/designsFilters")
    @Timed(name = "mobileDesignFiltersRequests", absolute = true)
    public Response suppliers(DesignsFiltersRequest request) {

        try {
            MobileDesignFiltersResponse responseData = designsFiltersRequestHandler.execute(request);
            return responseBuilder.build(responseData);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/designSuppliers")
    @Timed(name = "mobileDesignSuppliersRequests", absolute = true)
    public Response suppliers(DesignSuppliersRequest request) {

        try {
            MobileDesignSuppliersResponse responseData = designSuppliersResponseBuilder.build(request);
            return responseBuilder.build(responseData);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }
}
