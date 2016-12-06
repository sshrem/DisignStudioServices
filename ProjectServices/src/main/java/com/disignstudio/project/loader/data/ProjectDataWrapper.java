package com.disignstudio.project.loader.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ohadbenporat on 2/4/16.
 */
public class ProjectDataWrapper implements Serializable {

    private ProjectData project;
    private EntrepreneurData entrepreneur;
    private List<String> projectFeatures;
    private List<ApartmentTemplateData> apartmentTemplates;

    public ProjectDataWrapper(ProjectData project, EntrepreneurData entrepreneur, List<String> projectFeatures, List<ApartmentTemplateData> apartmentTemplates) {
        this.project = project;
        this.entrepreneur = entrepreneur;
        this.projectFeatures = projectFeatures;
        this.apartmentTemplates = apartmentTemplates;
    }

    public ProjectData getProject() {
        return project;
    }

    public void setProject(ProjectData project) {
        this.project = project;
    }

    public EntrepreneurData getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(EntrepreneurData entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public List<String> getProjectFeatures() {
        return projectFeatures;
    }

    public void setProjectFeatures(List<String> projectFeatures) {
        this.projectFeatures = projectFeatures;
    }

    public List<ApartmentTemplateData> getApartmentTemplates() {
        return apartmentTemplates;
    }

    public void setApartmentTemplates(List<ApartmentTemplateData> apartmentTemplates) {
        this.apartmentTemplates = apartmentTemplates;
    }
}
