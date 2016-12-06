package com.disignstudio.project.api;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.db.bean.Contact;
import com.disignstudio.project.db.bean.User;
import com.disignstudio.project.db.dao.IContactDao;
import com.disignstudio.project.db.dao.IUserDao;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ohadbenporat on 2/28/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class UsersAPITest {

    @Mock
    private IUserDao userDao;
    @Mock
    private IContactDao contactDao;

    private UsersAPI classOnTest;

    @Before
    public void setUp() throws Exception {
        classOnTest = new UsersAPI(userDao, contactDao);
    }

    @Test
    public void test() {

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "disignstudio",
                "api_key", "671623578364648",
                "api_secret", "u7Js93fJnrF1bvZ__RuGqUGRk7w"));

        String url = cloudinary.url().transformation(new Transformation().width(300).height(200).crop("fill").chain()
                .overlay("video:projects:shikunbinuidpchk:A1:1_Negev_CocreteBeige.mp4").flags("splice").width(300).height(200)).resourceType("video")
                .videoTag("projects/shikunbinuidpchk/A1/2_Negev_MidtownBeige.mp4", Maps.newHashMap());

        System.out.println(url);
    }

    @Test
    public void testRegisterLogin() {
        classOnTest.registerLogin(new User(0L, "myEmail", "myName", 1, new DateTime()));
        Mockito.verify(userDao, Mockito.times(1)).saveOrUpdate(Mockito.any(User.class));
    }

    @Test
    public void testAddContact() {

        Contact contact = new Contact(1, "name", "email", "phone");
        classOnTest.addContact(contact);
        Mockito.verify(contactDao, Mockito.times(1)).saveOrUpdate(contact);
    }
}
