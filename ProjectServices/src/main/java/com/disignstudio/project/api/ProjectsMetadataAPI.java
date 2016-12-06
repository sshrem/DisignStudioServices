package com.disignstudio.project.api;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.annotation.Timed;
import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.project.cache.FeaturesCacheLoader;
import com.disignstudio.project.loader.data.FeatureData;
import com.disignstudio.web.response.DesignStudioResponse;
import com.disignstudio.web.response.DesignStudioResponseBuilder;
import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ohadbenporat on 2/8/16.
 */
@Path("/api/metadata/")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectsMetadataAPI {

    private ICacheClient cacheClient;
    private FeaturesCacheLoader featuresCacheLoader;
    private DesignStudioResponseBuilder responseBuilder;

    @Inject
    public ProjectsMetadataAPI(ICacheClient cacheClient, FeaturesCacheLoader featuresCacheLoader) {
        this.cacheClient = cacheClient;
        this.featuresCacheLoader = featuresCacheLoader;
        this.responseBuilder = new DesignStudioResponseBuilder();
    }

    @GET
    @Path("/features")
    @Timed(name = "metadataFeaturesRequests", absolute = true)
    public Response getFeatures() {

        FeatureData data = cacheClient.getOrLoad(null, featuresCacheLoader, true);
        return responseBuilder.build(data);
    }
}
