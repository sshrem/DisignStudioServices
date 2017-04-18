package com.disignstudio.project.api;

import com.codahale.metrics.annotation.Timed;
import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.error.InvalidRequestException;
import com.disignstudio.project.api.handler.MobileDesignsFiltersRequestHandler;
import com.disignstudio.project.api.handler.MobileDesignsRequestHandler;
import com.disignstudio.project.api.request.DesignsFiltersRequest;
import com.disignstudio.project.api.request.DesignsRequest;
import com.disignstudio.project.api.response.MobileDesignFiltersResponse;
import com.disignstudio.project.api.response.MobileDesignsResponse;
import com.disignstudio.project.cache.ProjectCacheLoader;
import com.disignstudio.project.cache.pojo.ProjectCachedData;
import com.disignstudio.project.loader.data.ProjectDataWrapper;
import com.disignstudio.web.response.DesignStudioResponseBuilder;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ohadbenporat on 3/16/16.
 */
@Path("/api/")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectsAPI {

    private ICacheClient cacheClient;
    private ProjectCacheLoader projectCacheLoader;
    private DesignStudioResponseBuilder responseBuilder;
    private MobileDesignsRequestHandler designsResponseBuilder;
    private MobileDesignsFiltersRequestHandler designsFiltersRequestHandler;


    @Inject
    public ProjectsAPI(ICacheClient cacheClient, ProjectCacheLoader projectCacheLoader, MobileDesignsRequestHandler designsResponseBuilder, MobileDesignsFiltersRequestHandler
            designsFiltersRequestHandler) {
        this.cacheClient = cacheClient;
        this.projectCacheLoader = projectCacheLoader;
        this.responseBuilder = new DesignStudioResponseBuilder();
        this.designsResponseBuilder = designsResponseBuilder;
        this.designsFiltersRequestHandler = designsFiltersRequestHandler;
    }

    @GET
    @Path("/project")
    @Timed(name = "webProjectRequests", absolute = true)
    public Response getProject(@QueryParam("code") Long projectCode) {

        try {
            ProjectCachedData projectData = cacheClient.getOrLoad(projectCode, projectCacheLoader, true);
            return responseBuilder.build(projectData);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/designs")
    @Timed(name = "webDesignsRequests", absolute = true)
    public Response designsInfo(DesignsRequest request) {
        try {
            MobileDesignsResponse data = designsResponseBuilder.build(request);
            return responseBuilder.build(data);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/designsFilters")
    @Timed(name = "webDesignFiltersRequests", absolute = true)
    public Response suppliers(DesignsFiltersRequest request) {

        try {
            MobileDesignFiltersResponse responseData = designsFiltersRequestHandler.execute(request);
            return responseBuilder.build(responseData);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }




}
