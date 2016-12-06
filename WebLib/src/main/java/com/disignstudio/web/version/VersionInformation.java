package com.disignstudio.web.version;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class VersionInformation {

    private final String id;
    private final String implVersion;
    private final String specVersion;
    private final String revision;
    private final String createdBy;
    private final String builtBy;
    private final String specTitle;
    private final String specVendor;
    private final String buildJdk;
    private final String timestamp;

    public VersionInformation(final String id, final String implVersion, final String specVersion, final String revision,
                              final String createdBy, final String builtBy, final String specTitle, final String specVendor, final String buildJdk, final String timestamp) {
        this.id = id;
        this.implVersion = implVersion;
        this.specVersion = specVersion;
        this.revision = revision;
        this.createdBy = createdBy;
        this.builtBy = builtBy;
        this.specTitle = specTitle;
        this.specVendor = specVendor;
        this.buildJdk = buildJdk;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getImplVersion() {
        return implVersion;
    }

    public String getSpecVersion() {
        return specVersion;
    }

    public String getRevision() {
        return revision;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getBuiltBy() {
        return builtBy;
    }

    public String getSpecTitle() {
        return specTitle;
    }

    public String getSpecVendor() {
        return specVendor;
    }

    public String getBuildJdk() {
        return buildJdk;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
