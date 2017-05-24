package com.disignstudio.project.db.bean.helper;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by ohadbenporat on 5/17/16.
 */
public enum ERoom {

    BEDROOM(1),
    LIVINGROOM(2),
    BATHROOM(3),
    KITCHEN(4);

    private static Map<Integer, ERoom> mapIdToRoom;
    private int id;

    static {
        mapIdToRoom = Maps.newHashMap();
        for (ERoom room : ERoom.values()) {
            mapIdToRoom.put(room.getId(), room);
        }
    }

    ERoom(int id) {
        this.id = id;
    }

    public static ERoom getRoomById(int id) {
        return mapIdToRoom.get(id);
    }

    public int getId() {
        return id;
    }
}
