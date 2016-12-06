package com.disignstudio.web.version;

import com.disignstudio.common.text.StringTemplateWrapper;
import com.google.inject.Inject;

import java.util.List;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class VersionPrinter {

    private final JarVersionDetector versionDetector;
    private final StringTemplateWrapper stWrapper;

    @Inject
    public VersionPrinter(JarVersionDetector versionDetector, StringTemplateWrapper stWrapper) {
        this.versionDetector = versionDetector;
        this.stWrapper = stWrapper;
    }

    public String print() throws Exception {

        List<VersionInformation> jarVersions = versionDetector.getAllJarsVersion();
        String versionAsString = stWrapper.print("version.stg", "mainHtml", "versionInfo", jarVersions);
        return versionAsString;
    }
}
