package com.disignstudio.project.api.response;

import com.google.common.base.Objects;

/**
 * Created by ohadbenporat on 4/14/16.
 */
public class SupplierSummary {

    private long id;
    private String name;
    private String logo;
    private String url;

    public SupplierSummary(long id, String logo) {
        this(id, null, logo, null);
    }

    public SupplierSummary(long id, String name, String logo, String url) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, logo, url);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final SupplierSummary other = (SupplierSummary) obj;

        return Objects.equal(this.id, other.id) && Objects.equal(this.name, other.name) && Objects.equal(this.logo, other.logo) && Objects.equal(this.url, other.url);
    }
}
