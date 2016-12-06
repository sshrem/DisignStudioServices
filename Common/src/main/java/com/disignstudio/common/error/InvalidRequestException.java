package com.disignstudio.common.error;

import javax.ws.rs.WebApplicationException;

/**
 * Created by ohadbenporat on 2/27/16.
 */
public class InvalidRequestException extends WebApplicationException {

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
