package com.disignstudio.project.admin;

/**
 * Created by ohadbenporat on 9/22/16.
 */
public class OfferingRawData {

    private Long supplier;
    private String name;
    private String description;
    private Integer category;
    private boolean isStandard;
    private String image;

    public OfferingRawData(String line) {

        String[] fields = line.split("\\t");
        this.supplier = Long.parseLong(fields[1]);
        this.name = fields[2];
        this.description = fields[3];
        this.category = Integer.parseInt(fields[4]);
        this.isStandard = Boolean.parseBoolean(fields[5]);
        this.image = fields[6];
    }

    public Long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public boolean getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(boolean standard) {
        isStandard = standard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
