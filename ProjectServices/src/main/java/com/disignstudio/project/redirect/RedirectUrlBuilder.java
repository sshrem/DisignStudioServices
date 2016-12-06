package com.disignstudio.project.redirect;

import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.project.stats.EViewImagingSource;
import com.google.inject.Inject;

/**
 * Created by ohadbenporat on 4/17/16.
 */
public class RedirectUrlBuilder {

    private String redirectBaseUrl;

    @Inject
    public RedirectUrlBuilder(ConfigurationProvider configurationProvider) {
        this.redirectBaseUrl = configurationProvider.provideProperty("redirect.base.url");
    }

    public String build(long projectId, long aptTmplId, long designId, EViewImagingSource redirectFrom) {
        return String.format(redirectBaseUrl, projectId, aptTmplId, designId, redirectFrom.getSource());
    }
}
