package com.disignstudio.project.api;

import com.disignstudio.project.api.handler.*;
import com.disignstudio.project.api.request.DesignsRequest;
import com.disignstudio.project.api.request.ProjectRequest;
import com.disignstudio.project.api.response.MobileDesignsResponse;
import com.disignstudio.project.api.response.MobileProjectResponse;
import com.disignstudio.web.response.DesignStudioResponse;
import org.glassfish.jersey.message.internal.OutboundJaxrsResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by ohadbenporat on 2/28/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjectsContentAPITest {

    @Mock
    private MobileProjectRequestHandler projectResponseBuilder;
    @Mock
    private MobileDesignsRequestHandler designsResponseBuilder;
    @Mock
    private MobileDesignItemsResponseBuilder designItemsResponseBuilder;
    @Mock
    private MobileDesignSuppliersRequestHandler suppliersResponseBuilder;
    @Mock
    private MobileDesignsFiltersRequestHandler designFiltersHandler;

    private MobileContentAPI classOnTest;


    @Before
    public void setUp() throws Exception {
        classOnTest = new MobileContentAPI(projectResponseBuilder, suppliersResponseBuilder, designFiltersHandler, designsResponseBuilder);
    }

    @Test
    public void testProjectInfo() throws Exception {

        ProjectRequest request = new ProjectRequest(1L, true);
        MobileProjectResponse data = new MobileProjectResponse("title", "about", "", 1, 1, "", "", "", null, null, null);
        Mockito.when(projectResponseBuilder.build(request)).thenReturn(data);
        Response response = classOnTest.projectInfo(request);
        validate(response, data);
    }

    @Test
    public void testDesignsInfo() throws Exception {

        DesignsRequest request = new DesignsRequest(1L, 2L, null, null, true);
        MobileDesignsResponse data = new MobileDesignsResponse(1, "", null);
        Mockito.when(designsResponseBuilder.build(request)).thenReturn(data);
        Response response = classOnTest.designsInfo(request);
        validate(response, data);
    }

    private <T> void validate(Response response, T data) throws Exception {

        DesignStudioResponse<T> responseData = (DesignStudioResponse<T>) ((OutboundJaxrsResponse) response).getContext().getEntity();
        assertTrue("Wrong response status", responseData.isSuccess());
        assertEquals("Wrong response data", data, responseData.getData());
    }
}
