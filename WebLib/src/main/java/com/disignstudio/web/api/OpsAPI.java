package com.disignstudio.web.api;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.web.properties.PropertiesPrinter;
import com.disignstudio.web.version.VersionPrinter;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ohadbenporat on 1/11/16.
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class OpsAPI {

    private final VersionPrinter versionPrinter;
    private final PropertiesPrinter propertiesPrinter;
    private final ICacheClient cacheClient;

    @Inject
    public OpsAPI(VersionPrinter versionPrinter, PropertiesPrinter propertiesPrinter, ICacheClient cacheClient) {
        this.versionPrinter = versionPrinter;
        this.propertiesPrinter = propertiesPrinter;
        this.cacheClient = cacheClient;
    }

    @GET
    @Path("/ping")
    public String ping() {
        return "pong!";
    }

    @GET
    @Path("/properties")
    public String properties() {
        return propertiesPrinter.print();
    }

    @GET
    @Path("/version")
    public String version() throws Exception {
        return versionPrinter.print();
    }

    @GET
    @Path("/cache")
    public String version(@QueryParam("prefix") String prefix, @QueryParam("key") String key, @DefaultValue("false") @QueryParam("clear") boolean clear) throws Exception {

        EntityCacheKey cacheKey = new EntityCacheKey(key, prefix);
        String cacheValue = cacheClient.getStoredValue(cacheKey);
        if (cacheValue != null && clear) {
            cacheClient.clear(cacheKey);
        }

        return cacheValue != null ? cacheValue : "No cache for key: " + cacheKey;
    }
}
