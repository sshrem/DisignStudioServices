package com.disignstudio.common.cache;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by ohad on 10/22/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class InProcCacheClientTest {

    private static final Long KEY1 = 1L;
    private static final Long KEY2 = 2L;
    private static final String VALUE = "UnitTest";

    @Mock
    private ICacheLoader cacheLoader;

    private InProcCacheClient classOnTest;


    @Before
    public void setUp() throws Exception {

        Mockito.when(cacheLoader.getCacheValueType()).thenReturn(TypeToken.of(Object.class));
        Mockito.when(cacheLoader.generateKey(Mockito.eq(KEY1))).thenReturn(new EntityCacheKey<Object>(KEY1, ""));
        Mockito.when(cacheLoader.generateKey(Mockito.eq(KEY2))).thenReturn(new EntityCacheKey<Object>(KEY2, ""));
        Mockito.when(cacheLoader.load(KEY1)).thenReturn(VALUE);
        Mockito.when(cacheLoader.load(KEY2)).thenReturn(VALUE);
        Mockito.when(cacheLoader.getCacheExpiration()).thenReturn(1000);
        classOnTest = new InProcCacheClient("unitTestCache", new Gson());
    }

    @Test
    public void testGetAndLoadWithPreloadedData() throws Exception {

        classOnTest.put(new EntityCacheKey(KEY1, ""), VALUE, 50000);

        String valueFromCache = classOnTest.getOrLoad(KEY1, cacheLoader, true);
        assertEquals("Wrong value read from cache", VALUE, valueFromCache);
        Mockito.verify(cacheLoader, Mockito.times(0)).load(Mockito.any(EntityCacheKey.class));
    }

    @Test
    public void testGetAndLoadWithoutPreloadedData() throws Exception {

        String firstValueFromCache = classOnTest.getOrLoad(KEY2, cacheLoader, true);
        String secondValueFromCache = classOnTest.getOrLoad(KEY2, cacheLoader, true);

        assertEquals("Wrong value read from cache", VALUE, firstValueFromCache);
        assertEquals("Wrong value read from cache", VALUE, secondValueFromCache);
        Mockito.verify(cacheLoader, Mockito.times(1)).load(Mockito.eq(KEY2));
    }
}
