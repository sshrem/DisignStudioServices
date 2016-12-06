package com.disignstudio.common.configuration;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.common.collect.Maps;
import io.dropwizard.Configuration;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.SimpleServerFactory;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ohadbenporat on 1/10/16.
 */
public class ConfigurationProvider extends Configuration {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationProvider.class);

    private static final String DEFAULT_CONFIG_FILE_NAME = "configuration.yml";
    private static final Lock lock = new ReentrantLock();
    private static final String DEFAULT_CONFIG_NODE_NAME = "DisignStudio";
    private static final String DEFAULT_CONFIG_ENV = "all";

    private Map<String, String> mapPropertyNameToValue = Maps.newHashMap();

    public ConfigurationProvider() {

        int port = Integer.parseInt(System.getenv("PORT"));
        String appName = System.getenv("APP_NAME");
        String appEnv = System.getenv("APP_ENV");
        System.out.println("Running services with ports: " + port);
        System.out.println("Running services with config§: " + appName + " - " + appEnv);

        initConfiguration(DEFAULT_CONFIG_FILE_NAME, appName, appEnv, port);
    }

    public ConfigurationProvider(String configFile, String appName, String appEnv, Integer appPort) {

        initConfiguration(configFile, appName, appEnv, appPort);
    }


    private Map<String, String> initConfiguration(String configFile, String appName, String appEnv, int appPort) {
        if (mapPropertyNameToValue.isEmpty()) {
            synchronized (lock) {
                try {

                    // Configuration DropWizard server.
                    setServerFactory(new SimpleServerFactory());
                    ((HttpConnectorFactory) ((SimpleServerFactory) getServerFactory()).getConnector()).setPort(appPort);
                    ((SimpleServerFactory) getServerFactory()).setApplicationContextPath("/");

                    // Configure App server
                    if (mapPropertyNameToValue.isEmpty()) {
                        if (StringUtils.isEmpty(appName) || StringUtils.isEmpty(appEnv)) {
                            throw new RuntimeException("Can't load configuration. Application name or environment is missing");
                        }

                        loadConfiguration(appName, appEnv, configFile);
                    }
                } catch (Exception e) {
                    logger.error("Failed to load configuration", e);
                    throw new RuntimeException(e);
                }
            }
        }

        return mapPropertyNameToValue;
    }

    private void loadConfiguration(String appName, String appEnv, String configFileName) throws FileNotFoundException {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configFileName);
        Yaml yaml = new Yaml();
        Map<String, Map<List<String>, Map<String, Object>>> data = ((Map<String, Map<List<String>, Map<String, Object>>>) yaml.load(inputStream));
        loadProperties(data.get(DEFAULT_CONFIG_NODE_NAME), DEFAULT_CONFIG_ENV);
        loadProperties(data.get(DEFAULT_CONFIG_NODE_NAME), appEnv);
        loadProperties(data.get(appName), DEFAULT_CONFIG_ENV);
        loadProperties(data.get(appName), appEnv);
    }

    private void loadProperties(Map<List<String>, Map<String, Object>> propsPerEnv, String appEnv) {
        if (MapUtils.isEmpty(propsPerEnv)) {
            return;
        }

        for (Map.Entry<List<String>, Map<String, Object>> entry : propsPerEnv.entrySet()) {
            if (entry.getKey().get(0).equals(appEnv)) {
                for (Map.Entry<String, Object> propEntry : entry.getValue().entrySet()) {
                    mapPropertyNameToValue.put(propEntry.getKey(), propEntry.getValue().toString());
                }
            }
        }
    }

    public Map<String, String> getMapPropertyNameToValue() {
        return mapPropertyNameToValue;
    }

    public String provideProperty(String propName) {
        return mapPropertyNameToValue.get(propName);
    }

    public Integer providePropertyAsInt(String propName) {
        String propValue = provideProperty(propName);
        return StringUtils.isEmpty(propValue) ? null : Integer.parseInt(propValue);
    }

    public Boolean providePropertyAsBoolean(String propName) {
        String propValue = provideProperty(propName);
        return StringUtils.isEmpty(propValue) ? null : Boolean.parseBoolean(propValue);
    }
}
