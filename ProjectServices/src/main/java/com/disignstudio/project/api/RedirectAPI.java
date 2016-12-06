package com.disignstudio.project.api;

import com.codahale.metrics.annotation.Timed;
import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.common.error.InvalidRequestException;
import com.disignstudio.project.api.response.ImagingRedirectSummary;
import com.disignstudio.project.redirect.RedirectHandler;
import com.disignstudio.web.filters.UuidCookieGenerator;
import com.google.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;
import java.net.URI;

/**
 * Created by ohadbenporat on 4/13/16.
 */
@Path("/api/redirect/")
@Produces(MediaType.TEXT_HTML)
public class RedirectAPI {

    private final String imagingRedirectTemplate;
    private UuidCookieGenerator uuidCookieGenerator;
    private RedirectHandler redirectHandler;

    @Inject
    public RedirectAPI(UuidCookieGenerator uuidCookieGenerator, ConfigurationProvider configurationProvider, RedirectHandler redirectHandler) {
        this.uuidCookieGenerator = uuidCookieGenerator;
        this.redirectHandler = redirectHandler;
        this.imagingRedirectTemplate = configurationProvider.provideProperty("redirect.imaging-template");
    }

    @GET
    @Path("/imaging")
    @Timed(name = "redirectImaging", absolute = true)
    public String redirect(@QueryParam("pId") long projectId, @QueryParam("atId") long aptTmplId, @QueryParam("dId") long designId, @QueryParam("src") String source,
                           @Context HttpServletRequest request) {

        try {
            String userAgent = request.getHeader("user-agent");
            NewCookie uuidCookie = uuidCookieGenerator.loadOrCreate(request);
            ImagingRedirectSummary redirectInfo = redirectHandler.getRedirectedInfo(source, projectId,aptTmplId, designId, request.getRemoteAddr(), userAgent, uuidCookie.getValue());
            String response = imagingRedirectTemplate.replace("$TITLE$", redirectInfo.getTitle()).replace("$URL$", redirectInfo.getUrl());
            return response;
        } catch (Exception e) {
            throw new InvalidRequestException(e.getMessage(), e);
        }
    }
}
