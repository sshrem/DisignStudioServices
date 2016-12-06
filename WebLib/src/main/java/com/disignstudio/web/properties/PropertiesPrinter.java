package com.disignstudio.web.properties;

import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.common.text.StringTemplateWrapper;
import com.google.inject.Inject;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class PropertiesPrinter {

    private final StringTemplateWrapper stWrapper;
    private ConfigurationProvider configurationProvider;

    @Inject
    public PropertiesPrinter(StringTemplateWrapper stWrapper, ConfigurationProvider configurationProvider) {
        this.stWrapper = stWrapper;
        this.configurationProvider = configurationProvider;
    }

    public String print() {

        String result = stWrapper.print("properties.stg", "mainHtml", "props", configurationProvider.getMapPropertyNameToValue());
        return result;
    }
}
