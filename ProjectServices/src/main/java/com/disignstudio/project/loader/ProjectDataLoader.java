package com.disignstudio.project.loader;

import com.disignstudio.project.db.bean.ApartmentTemplate;
import com.disignstudio.project.db.bean.Contact;
import com.disignstudio.project.db.bean.Entreprenuer;
import com.disignstudio.project.db.bean.ProjectFeature;
import com.disignstudio.project.db.bean.VideoDetails;
import com.disignstudio.project.db.bean.extended.ProjectExtended;
import com.disignstudio.project.db.dao.*;
import com.disignstudio.project.loader.data.*;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ohadbenporat on 2/4/16.
 */
public class ProjectDataLoader {

    private IProjectDao projectDao;
    private IApartmentTemplateDao apartmentTemplateDao;
    private IEntrepreneurDao entrepreneurDao;
    private IProjectFeatureDao projectFeatureDao;
    private IContactDao contactDao;
    private IVideoDetailsDao videoDetailsDao;

    @Inject
    public ProjectDataLoader(IProjectDao projectDao, IApartmentTemplateDao apartmentTemplateDao, IEntrepreneurDao entrepreneurDao,
                             IProjectFeatureDao projectFeatureDao, IContactDao contactDao, IVideoDetailsDao videoDetailsDao) {
        this.projectDao = projectDao;
        this.apartmentTemplateDao = apartmentTemplateDao;
        this.entrepreneurDao = entrepreneurDao;
        this.projectFeatureDao = projectFeatureDao;
        this.contactDao = contactDao;
        this.videoDetailsDao = videoDetailsDao;
    }

    public ProjectDataWrapper loadProjectData(long projId) {

        ProjectExtended project = projectDao.findById(projId);
        if (project == null) {
            throw new IllegalArgumentException("projId is invalid:" + projId);
        }

        ContactData projectSalesContact = loadContact(project.getSalesContactId());

        ProjectData projectData = new ProjectData(project.getId(), project.getCode(), project.getName(), project.getAddress(), project.getCity(),
                project.getCountry(), project.getCityId(), project.getCountryId(), project.getLon(), project.getLat(), project.isActive(), project.getLogo(), project.getAbout(), projectSalesContact);

        EntrepreneurData entreprenuer = loadEntreprenuer(project.getEntrepreneur());
        List<ApartmentTemplateData> apartmentTemplates = loadApartmentTemplates(project.getId());
        List<String> projectFeatures = loadProjectFeatures(project.getId());
        List<VideoDetailsData> videoDetails = loadVideoDetails(project.getId());

        return new ProjectDataWrapper(projectData, entreprenuer, projectFeatures, apartmentTemplates, videoDetails);
    }

    private List<String> loadProjectFeatures(long projectId) {

        List<ProjectFeature> features = projectFeatureDao.findByProjectId(projectId);

        List<String> featuresCodes = Lists.newArrayList();
        features.forEach(feat -> {
            featuresCodes.add(feat.getFeatureDescription());
        });

        return featuresCodes;
    }

    private ContactData loadContact(long contactId) {
        Contact contact = contactDao.findById(contactId);
        return new ContactData(contact.getPhone());
    }

    private List<ApartmentTemplateData> loadApartmentTemplates(long projectId) {
        List<ApartmentTemplate> aptmtTemplates = apartmentTemplateDao.findByProject(projectId);

        List<ApartmentTemplateData> allData = Lists.newArrayList();
        for (ApartmentTemplate apt : aptmtTemplates) {
            ApartmentTemplateData data = new ApartmentTemplateData(apt.getId(), apt.getCode(), apt.getName(), apt.getImage(), apt.getNumOfRooms());
            allData.add(data);
        }

        return allData;
    }

    private List<VideoDetailsData> loadVideoDetails(long projectId){
        List<VideoDetails> videoDetails = videoDetailsDao.findByProject(projectId);
        return videoDetails.stream().map(v -> new VideoDetailsData(v.getId(), v.getProjectId(), v.getApartmentTemplateId(), v.getRoomId(), v.getVideoLength()))
            .collect(Collectors.toList());
    }

    private EntrepreneurData loadEntreprenuer(long entrepreneurId) {
        Entreprenuer entreprenuer = entrepreneurDao.findById(entrepreneurId);
        return new EntrepreneurData(entrepreneurId, entreprenuer.getName(), entreprenuer.getLogo(), entreprenuer.getAbout());
    }
}
