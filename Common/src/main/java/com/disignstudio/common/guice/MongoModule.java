package com.disignstudio.common.guice;

import com.disignstudio.common.configuration.ConfigurationProvider;
import com.google.common.collect.Lists;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.List;

/**
 * Created by ohadbenporat on 3/31/16.
 */
public class MongoModule implements Module {

    private ConfigurationProvider configuration;

    public MongoModule(ConfigurationProvider configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(MongoOperations.class).toInstance(generateMongoOperations());
    }

    private MongoOperations generateMongoOperations() {

        try {
            String mongoURI = configuration.provideProperty("mongodb.stats.uri");
            MongoClientURI mongoClientURI = new MongoClientURI(mongoURI);
            MongoDbFactory factory = new SimpleMongoDbFactory(mongoClientURI);
            return new MongoTemplate(factory);
        } catch (Exception e) {
            throw new RuntimeException("Can't connect to mongo");
        }
    }
}
