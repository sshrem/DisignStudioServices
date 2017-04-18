package com.disignstudio.project.api.response;

/**
 * Created by shrem on 4/18/17.
 */
public class CollectionStats {
    private Long count;
    private Long userCount;
    private Long entrepreneurUserCount;
    private Double averagePerUser;
    private Double averagePerEntrepreneurUser;


    public CollectionStats(Long count, Long userCount, Long entrepreneurUserCount, Double averagePerUser, Double averagePerEntrepreneurUser) {
        this.count = count;
        this.userCount = userCount;
        this.entrepreneurUserCount = entrepreneurUserCount;
        this.averagePerUser = averagePerUser;
        this.averagePerEntrepreneurUser = averagePerEntrepreneurUser;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Long getEntrepreneurUserCount() {
        return entrepreneurUserCount;
    }

    public void setEntrepreneurUserCount(Long entrepreneurUserCount) {
        this.entrepreneurUserCount = entrepreneurUserCount;
    }

    public Double getAveragePerUser() {
        return averagePerUser;
    }

    public void setAveragePerUser(Double averagePerUser) {
        this.averagePerUser = averagePerUser;
    }

    public Double getAveragePerEntrepreneurUser() {
        return averagePerEntrepreneurUser;
    }

    public void setAveragePerEntrepreneurUser(Double averagePerEntrepreneurUser) {
        this.averagePerEntrepreneurUser = averagePerEntrepreneurUser;
    }
}
