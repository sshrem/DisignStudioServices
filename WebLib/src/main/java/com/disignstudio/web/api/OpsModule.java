package com.disignstudio.web.api;

import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.common.text.StringTemplateWrapper;
import com.disignstudio.web.properties.PropertiesPrinter;
import com.disignstudio.web.version.JarVersionDetector;
import com.disignstudio.web.version.VersionPrinter;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class OpsModule implements Module {

    private ConfigurationProvider configurationProvider;

    public OpsModule(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    @Override
    public void configure(Binder binder) {
        JarVersionDetector versionDetector = new JarVersionDetector();
        StringTemplateWrapper stw = new StringTemplateWrapper();
        binder.bind(VersionPrinter.class).toInstance(new VersionPrinter(versionDetector, stw));

        binder.bind(PropertiesPrinter.class).toInstance(new PropertiesPrinter(stw, configurationProvider));
    }
}
