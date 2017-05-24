package com.disignstudio.project.admin;

import com.disignstudio.project.db.bean.ApartmentTemplate;
import com.disignstudio.project.db.bean.Design;
import com.disignstudio.project.db.bean.DesignItem;
import com.disignstudio.project.db.dao.IApartmentTemplateDao;
import com.disignstudio.project.db.dao.IDesignDao;
import com.disignstudio.project.db.dao.IDesignItemDao;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by ohadbenporat on 4/10/16.
 */
public class DesignsUploader {

    private static final String FILE_NAME = "/Users/shrem/dev/ds/designs.tsv";

    private IApartmentTemplateDao apartmentTemplateDao;
    private IDesignDao designDao;
    private IDesignItemDao designItemDao;
    private long apartmentTemplateId;
    private long designId;

    @Inject
    public DesignsUploader(IApartmentTemplateDao apartmentTemplateDao, IDesignDao designDao, IDesignItemDao designItemDao) {
        this.apartmentTemplateDao = apartmentTemplateDao;
        this.designDao = designDao;
        this.designItemDao = designItemDao;
    }

    public void upload() throws Exception {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(FILE_NAME));
            br.readLine(); // Skip headers Line

            String line = br.readLine();
            while (StringUtils.isNoneBlank(line)) {
                processLine(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }

    private void processLine(String line) throws Exception {

        DesignsRawData data = new DesignsRawData(line);


        extractApartmentTemplateId(data);

        // Add Design
        addDesign(data);

        // Add design item
        addDesignItem(data);
    }

    private void addDesignItem(DesignsRawData data) {

        DesignItem item = new DesignItem(designId, data.getOfferingId(), data.getRoom());
        designItemDao.saveOrUpdate(item);
    }

    private void addDesign(DesignsRawData data) throws Exception {

        if (StringUtils.isBlank(data.getDesignName())) {
            return;
        }

        Design design = new Design(data.getDesignerId(), apartmentTemplateId, data.getDesignName(), data.getDesignImg(), data.getFacebookVideoUrl());
        this.designId = designDao.saveOrUpdate(design);
    }

    private void extractApartmentTemplateId(DesignsRawData data) {

        if (StringUtils.isNoneBlank(data.getAptTmplCode())) {
            ApartmentTemplate aptTmpl = apartmentTemplateDao.findByCode(data.getAptTmplCode());
            this.apartmentTemplateId = aptTmpl.getId();
        }
    }
}
