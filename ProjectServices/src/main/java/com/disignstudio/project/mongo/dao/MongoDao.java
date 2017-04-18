package com.disignstudio.project.mongo.dao;

import com.disignstudio.project.api.response.CollectionStats;
import com.disignstudio.project.mongo.bean.VideoView;
import com.disignstudio.project.mongo.pojo.Average;
import com.disignstudio.project.mongo.pojo.Count;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

/**
 * Created by shrem on 4/18/17.
 */
public abstract class MongoDao<T> {
    private MongoOperations mongoOperation;

    public MongoDao(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    protected abstract String getCollectionName();

    public void insert(T data) {
        mongoOperation.insert(data);
    }

    public CollectionStats getStats(Long projectId){
        Count viewCount = getTotalCount();
        Count userCount = getUserCount("userId");
        Count entrepreneurUserCount = getUserCount("entrepreneurUserId");
        Average averagePerUser = getAveragePerUser("userId");
        Average averagePerEntrepreneurUser = getAveragePerUser("entrepreneurUserId");
        return new CollectionStats(viewCount.getCount(), userCount.getCount(), entrepreneurUserCount.getCount(),
                averagePerUser.getAvg(), averagePerEntrepreneurUser.getAvg());
    }

    private Count getTotalCount() {
        TypedAggregation<VideoView> aggregation = newAggregation(VideoView.class,
                group("projectId").count().as("count")
        );

        AggregationResults<Count> results = mongoOperation.aggregate( aggregation, getCollectionName(), Count.class);
        if (results.getMappedResults().size()>0){
            return results.getMappedResults().get(0);
        }

        return new Count(0L, 0L);
    }

    private Count getUserCount(String userId) {
        TypedAggregation<VideoView> aggregation = newAggregation(VideoView.class,
                group(userId).count().as("count"),
                group().count().as("count")
        );

        AggregationResults<Count> results = mongoOperation.aggregate( aggregation, getCollectionName(), Count.class);
        if (results.getMappedResults().size()>0){
            return results.getMappedResults().get(0);
        }

        return new Count(0L, 0L);
    }

    private Average getAveragePerUser(String userId) {
        TypedAggregation<VideoView> aggregation = newAggregation(VideoView.class,
                group(userId).count().as("count"),
                group().avg("count").as("avg")
        );

        AggregationResults<Average> results = mongoOperation.aggregate( aggregation, getCollectionName(), Average.class);
        if (results.getMappedResults().size()>0){
            return results.getMappedResults().get(0);
        }

        return new Average(0l, 0.0);
    }

}
