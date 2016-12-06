package com.disignstudio.project.loader.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignDataWrapper implements Serializable{

    private long apartmentTemplateId;
    private List<DesignData> designs;

    public DesignDataWrapper(long apartmentTemplateId, List<DesignData> designs) {
        this.apartmentTemplateId = apartmentTemplateId;
        this.designs = designs;
    }

    public long getApartmentTemplateId() {
        return apartmentTemplateId;
    }

    public void setApartmentTemplateId(long apartmentTemplateId) {
        this.apartmentTemplateId = apartmentTemplateId;
    }

    public List<DesignData> getDesigns() {
        return designs;
    }

    public void setDesigns(List<DesignData> designs) {
        this.designs = designs;
    }
}
