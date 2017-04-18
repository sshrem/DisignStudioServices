package com.disignstudio.project.mongo.pojo;

/**
 * Created by shrem on 4/18/17.
 */
public class Count {
    Long _id;
    Long count;

    public Count(Long _id, Long count) {
        this._id = _id;
        this.count = count;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
