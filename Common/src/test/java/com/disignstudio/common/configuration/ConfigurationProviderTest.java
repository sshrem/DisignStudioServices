package com.disignstudio.common.configuration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by ohadbenporat on 1/10/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfigurationProviderTest {

    private static final String CONFIG_FILE_NAME = "unit-test-config.yml";
    private ConfigurationProvider classOnTest;

    @Before
    public void setUp() throws Exception {
        System.setProperty("app.config", CONFIG_FILE_NAME);
        System.setProperty("app.server.port", "8080");
    }

    @Test(expected = RuntimeException.class)
    public void testNoAppNameBeenSet() {
        classOnTest = new ConfigurationProvider(CONFIG_FILE_NAME, "testApp", null, 8080);
    }

    @Test(expected = RuntimeException.class)
    public void testNoAppEnvBeenSet() {
        classOnTest = new ConfigurationProvider(CONFIG_FILE_NAME, null, "dev", 8080);
    }

    @Test
    public void testAppHasNoConfiguration() {
        classOnTest = new ConfigurationProvider(CONFIG_FILE_NAME,"na","dev",8080);
        Map<String, String> properties = classOnTest.getMapPropertyNameToValue();
        assertEquals("Wrong property value been loaded", "1a", properties.get("prop1"));
        assertEquals("Wrong property value been loaded", "2b", properties.get("prop2"));
        assertEquals("Wrong property value been loaded", "3b", properties.get("prop3"));
        assertEquals("Wrong property value been loaded", "4b", properties.get("prop4"));
    }

    @Test
    public void testLoadBothDefaultAndAppProps() {
        classOnTest = new ConfigurationProvider(CONFIG_FILE_NAME,"testApp","dev",8080);
        Map<String, String> properties = classOnTest.getMapPropertyNameToValue();
        assertEquals("Wrong property value been loaded", "1a", properties.get("prop1"));
        assertEquals("Wrong property value been loaded", "2b", properties.get("prop2"));
        assertEquals("Wrong property value been loaded", "3c", properties.get("prop3"));
        assertEquals("Wrong property value been loaded", "4d", properties.get("prop4"));
        assertEquals("Wrong property value been loaded", "5d", properties.get("prop5"));
        assertEquals("Wrong property value been loaded", null, properties.get("prop6"));
    }

    @Test
    public void testLoadBothDefaultAndAppProps2() {
        classOnTest = new ConfigurationProvider(CONFIG_FILE_NAME,"testApp","test",8080);
        Map<String, String> properties = classOnTest.getMapPropertyNameToValue();
        assertEquals("Wrong property value been loaded", "1a", properties.get("prop1"));
        assertEquals("Wrong property value been loaded", "2a", properties.get("prop2"));
        assertEquals("Wrong property value been loaded", "3c", properties.get("prop3"));
        assertEquals("Wrong property value been loaded", "4a", properties.get("prop4"));
        assertEquals("Wrong property value been loaded", "5e", properties.get("prop5"));
        assertEquals("Wrong property value been loaded", "6f", properties.get("prop6"));
    }
}
