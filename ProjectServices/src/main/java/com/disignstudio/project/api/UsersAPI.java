package com.disignstudio.project.api;

import com.codahale.metrics.annotation.Timed;
import com.disignstudio.project.db.bean.Contact;
import com.disignstudio.project.db.bean.User;
import com.disignstudio.project.db.dao.IContactDao;
import com.disignstudio.project.db.dao.IUserDao;
import com.disignstudio.web.response.DesignStudioResponseBuilder;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ohadbenporat on 2/14/16.
 */
@Path("/api/users/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersAPI {

    private IUserDao userDao;
    private IContactDao contactDao;
    private DesignStudioResponseBuilder responseBuilder;

    @Inject
    public UsersAPI(IUserDao userDao, IContactDao contactDao) {
        this.userDao = userDao;
        this.contactDao = contactDao;
        this.responseBuilder = new DesignStudioResponseBuilder();
    }


    @POST
    @Path("/registerLogin")
    @Timed(name = "registerLoginRequests", absolute = true)
    public Response registerLogin(User user) {

        long data = userDao.saveOrUpdate(user);
        return responseBuilder.build(data);
    }

    @POST
    @Path("/addContact")
    public Response addContact(Contact contact) {
        contactDao.saveOrUpdate(contact);
        return responseBuilder.build(null);
    }
}
