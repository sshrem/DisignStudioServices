package com.disignstudio.project.db.bean.extended;

import com.disignstudio.project.db.bean.Design;

/**
 * Created by ohadbenporat on 2/15/16.
 */
public class DesignExtended extends Design {

    private String designerName;
    private String designerImage;

    public DesignExtended(long id, long designerId, long apartmentTemplateId, String title, String imagingCode,
                          String facebookVideoUrl, String designerName, String designerImage) {

        super(id, designerId, apartmentTemplateId, title, imagingCode, facebookVideoUrl);
        this.designerName = designerName;
        this.designerImage = designerImage;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getDesignerImage() {
        return designerImage;
    }

    public void setDesignerImage(String designerImage) {
        this.designerImage = designerImage;
    }
}
