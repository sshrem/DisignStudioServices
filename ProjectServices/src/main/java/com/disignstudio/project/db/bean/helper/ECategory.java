package com.disignstudio.project.db.bean.helper;

/**
 * Created by ohadbenporat on 5/22/16.
 */
public enum ECategory {

    FLOORING(1000),
    CLADDING(2000),
    KITCHEN_CUPBOARD(3000);

    private final int id;

    ECategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
