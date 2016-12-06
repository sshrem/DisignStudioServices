package com.disignstudio.project.api.request;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by ohadbenporat on 5/12/16.
 */
public class DesignItemFilter {

    private long offer = 0L;
    private int room = 0;

    public long getOffer() {
        return offer;
    }

    public void setOffer(long offer) {
        this.offer = offer;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final DesignItemFilter other = (DesignItemFilter) obj;
        return Objects.equal(this.room, other.room)
                && Objects.equal(this.offer, other.offer);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(room, offer);
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("room", room)
                .add("offer", offer)
                .toString();

    }
}
