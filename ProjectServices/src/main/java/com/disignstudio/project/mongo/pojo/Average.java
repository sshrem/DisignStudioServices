package com.disignstudio.project.mongo.pojo;

/**
 * Created by shrem on 4/18/17.
 */
public class Average {
    Long _id;
    Double avg;

    public Average(Long _id, Double avg) {
        this._id = _id;
        this.avg = avg;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
