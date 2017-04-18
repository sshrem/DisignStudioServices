package com.disignstudio.web.response;

import javax.ws.rs.core.Response;

/**
 * Created by ohadbenporat on 3/16/16.
 */
public class DesignStudioResponseBuilder {

    public <T> Response build(T data) {
        return Response.ok(new DesignStudioResponse(data)).header("Access-Control-Allow-Origin", "*").build();
    }

    public Response error() {
        return Response.serverError().header("Access-Control-Allow-Origin", "*").build();
    }

}
