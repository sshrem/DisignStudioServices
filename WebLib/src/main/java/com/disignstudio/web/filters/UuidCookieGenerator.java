package com.disignstudio.web.filters;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.NewCookie;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by ohadbenporat on 4/13/16.
 */
public class UuidCookieGenerator {

    private static final String COOKIE_NAME = "UUID_COOKIE";
    private static final String COOKIE_DOMAIN = "disignstudio.com";
    private static final int MAX_AGE = 60 * 60 * 24 * 360;

    public NewCookie loadOrCreate(HttpServletRequest request) throws IOException {
        Cookie[] existingCookies = request.getCookies();

        if (ArrayUtils.isEmpty(existingCookies)) {
            return generateNewCookie();
        }
        for (Cookie cookie : existingCookies) {
            if (cookie.getName().equals(COOKIE_NAME)) {
                return new NewCookie(cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getDomain(), cookie.getComment(), cookie.getMaxAge(), cookie.getSecure());
            }
        }

        return generateNewCookie();
    }

    private NewCookie generateNewCookie() {
        String uuid = UUID.randomUUID().toString();
        return new NewCookie(COOKIE_NAME, uuid, null, COOKIE_DOMAIN, null, MAX_AGE, false);
    }
}
