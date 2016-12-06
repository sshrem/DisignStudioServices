package com.disignstudio.project.db.bean.helper;

/**
 * Created by ohadbenporat on 5/22/16.
 */
public enum EDesignFilter {

    BEDROOM_FLOOR("choosebedroomfloor", ERoom.BEDROOM, ECategory.FLOORING),
    LIVING_FLOOR("chooselivingroomfloor", ERoom.LIVINGROOM, ECategory.FLOORING),
    BATHROOM_FLOOR("choosebathroomfloor", ERoom.BATHROOM, ECategory.FLOORING),
    BATHROOM_CLADDING("choosebathroomcladding", ERoom.BATHROOM, ECategory.CLADDING);

    private final String title;
    private final ERoom room;
    private final ECategory category;

    EDesignFilter(String title, ERoom room, ECategory category) {
        this.title = title;
        this.room = room;
        this.category = category;
    }

    public static EDesignFilter findByRoomAndCategory(int roomId, int category) {

        for (EDesignFilter filter : EDesignFilter.values()) {
            if (filter.getRoom().getId() == roomId && filter.getCategory().getId() == category) {
                return filter;
            }
        }

        return null;
    }

    public String getTitle() {
        return title;
    }

    public ERoom getRoom() {
        return room;
    }

    public ECategory getCategory() {
        return category;
    }
}
