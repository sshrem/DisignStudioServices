package com.disignstudio.project.api;

import com.codahale.metrics.annotation.Timed;
import com.disignstudio.project.api.request.*;
import com.disignstudio.project.api.response.ProjectStats;
import com.disignstudio.project.stats.ProjectStatsHandler;
import com.disignstudio.web.response.DesignStudioResponse;
import com.disignstudio.web.response.DesignStudioResponseBuilder;
import com.google.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ohadbenporat on 3/31/16.
 */
@Path("/api/stats/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatsAPI {

    private ProjectStatsHandler statsHandler;
    private DesignStudioResponseBuilder responseBuilder;

    @Inject
    public StatsAPI(ProjectStatsHandler statsHandler, DesignStudioResponseBuilder responseBuilder) {
        this.statsHandler = statsHandler;
        this.responseBuilder = responseBuilder;
    }

    @POST
    @Path("/recordImagingView")
    @Timed(name = "statsImageingViewRequests", absolute = true)
    public Response recordImagingView(ImagingViewRequest reqData, @Context HttpServletRequest request) {

        try {
            statsHandler.recordImagingView(reqData, request.getRemoteAddr());
            return responseBuilder.build(DesignStudioResponse.SUCCESS_MSG);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/recordUserAction")
    @Timed(name = "statsUserActionRequests", absolute = true)
    public Response recordUserAction(UserActionRequest reqData, @Context HttpServletRequest request) {

        try {
            statsHandler.recordUserAction(reqData, request.getRemoteAddr());
            return responseBuilder.build(DesignStudioResponse.SUCCESS_MSG);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/recordViewSupplier")
    @Timed(name = "statsUserActionRequests", absolute = true)
    public Response recordViewSupplier(ViewSupplierRequest reqData, @Context HttpServletRequest request) {

        try {
            statsHandler.recordViewSupplier(reqData, request.getRemoteAddr());
            return responseBuilder.build(DesignStudioResponse.SUCCESS_MSG);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/recordVideoView")
    @Timed(name = "statsVideoViewRequests", absolute = true)
    public Response recordVideoView(VideoViewRequest reqData, @Context HttpServletRequest request) {

        try {
            statsHandler.recordVideoView(reqData, request.getRemoteAddr(), request.getHeader("User-Agent"));
            return responseBuilder.build(DesignStudioResponse.SUCCESS_MSG);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/recordFacebookShare")
    @Timed(name = "statsFacebookShareRequests", absolute = true)
    public Response recordFacebookShare(FacebookShareRequest reqData, @Context HttpServletRequest request) {

        try {
            statsHandler.recordFacebookShare(reqData, request.getRemoteAddr(), request.getHeader("User-Agent"));
            return responseBuilder.build(DesignStudioResponse.SUCCESS_MSG);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @POST
    @Path("/recordVisit")
    @Timed(name = "statsVisitRequests", absolute = true)
    public Response recordVisit(VisitRequest reqData, @Context HttpServletRequest request) {

        try {
            statsHandler.recordVisit(reqData, request.getRemoteAddr(), request.getHeader("User-Agent"));
            return responseBuilder.build(DesignStudioResponse.SUCCESS_MSG);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }

    @GET
    @Path("/projectStats")
    @Timed(name = "projectStatsRequests", absolute = true)
    public Response projectStats(@QueryParam("id") Long projectId) {

        try {
            ProjectStats stats = statsHandler.getProjectStats(projectId);
            return responseBuilder.build(stats);
        } catch (Exception e) {
            return responseBuilder.error();
        }
    }


}
