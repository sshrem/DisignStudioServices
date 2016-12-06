package com.disignstudio.common.guice;

import com.disignstudio.common.configuration.ConfigurationProvider;
import com.google.inject.Binder;
import com.google.inject.Module;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by ohadbenporat on 1/10/16.
 */
public class DatabaseModule implements Module {

    private ConfigurationProvider configuration;

    public DatabaseModule(ConfigurationProvider configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(JdbcTemplate.class).toInstance(generateJdbcTemplate());
    }

    private JdbcTemplate generateJdbcTemplate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(configuration.provideProperty("mysql.rw.url"));
        dataSource.setUsername(configuration.provideProperty("mysql.rw.username"));
        dataSource.setPassword(configuration.provideProperty("mysql.rw.password"));
        dataSource.setDriverClassName(configuration.provideProperty("mysql.rw.driverClassName"));
        dataSource.setDefaultReadOnly(false);
        dataSource.setInitialSize(configuration.providePropertyAsInt("mysql.rw.initialSize"));
        dataSource.setMaxActive(configuration.providePropertyAsInt("mysql.rw.maxActive"));
        dataSource.setMaxWait(configuration.providePropertyAsInt("mysql.rw.maxWait"));
        dataSource.setMaxIdle(configuration.providePropertyAsInt("mysql.rw.maxIdle"));
        dataSource.setMinIdle(configuration.providePropertyAsInt("mysql.rw.minIdle"));
        dataSource.setValidationQuery(configuration.provideProperty("mysql.rw.validationQuery"));
        dataSource.setTestWhileIdle(configuration.providePropertyAsBoolean("mysql.rw.testWhileIdle"));
        dataSource.setTestOnBorrow(configuration.providePropertyAsBoolean("mysql.rw.testOnBorrow"));
        dataSource.setTestOnReturn(configuration.providePropertyAsBoolean("mysql.rw.testOnReturn"));
        dataSource.setMinEvictableIdleTimeMillis(configuration.providePropertyAsInt("mysql.rw.minEvitableIdleTimeMillis"));
        dataSource.setRemoveAbandoned(configuration.providePropertyAsBoolean("mysql.rw.removeAbandoned"));
        dataSource.setRemoveAbandonedTimeout(configuration.providePropertyAsInt("mysql.rw.removeAbandonedTimeout"));
        dataSource.setDefaultAutoCommit(configuration.providePropertyAsBoolean("mysql.rw.autoCommit"));
        dataSource.setNumTestsPerEvictionRun(configuration.providePropertyAsInt("mysql.rw.numTetsPerEvictionRun"));
        dataSource.setLogAbandoned(configuration.providePropertyAsBoolean("mysql.rw.logAbandoned"));

        return new JdbcTemplate(dataSource);
    }
}
