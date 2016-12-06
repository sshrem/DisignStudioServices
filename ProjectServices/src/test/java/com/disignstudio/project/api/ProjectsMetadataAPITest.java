package com.disignstudio.project.api;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.cache.ICacheLoader;
import com.disignstudio.project.cache.FeaturesCacheLoader;
import com.disignstudio.project.loader.data.FeatureData;
import com.disignstudio.web.response.DesignStudioResponse;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
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
public class ProjectsMetadataAPITest {

    @Mock
    private FeaturesCacheLoader featuresCacheLoader;
    @Mock
    private ICacheClient cacheClient;

    private ProjectsMetadataAPI classOnTest;


    @Before
    public void setUp() throws Exception {
        classOnTest = new ProjectsMetadataAPI(cacheClient, featuresCacheLoader);
    }

    @Test
    public void testGetFeatures() {
        FeatureData data = new FeatureData();
        data.addLanguage("he", Maps.newHashMap());
        Mockito.when(cacheClient.getOrLoad(Mockito.any(Object.class), Mockito.any(ICacheLoader.class), Mockito.anyBoolean())).thenReturn(data);
        Response response = classOnTest.getFeatures();
        DesignStudioResponse<FeatureData> responseData = (DesignStudioResponse<FeatureData>) ((OutboundJaxrsResponse) response).getContext().getEntity();
        assertTrue("Wrong response status", responseData.isSuccess());
        assertEquals("Wrong response data", 1, responseData.getData().getTranslations().size());
    }
}
