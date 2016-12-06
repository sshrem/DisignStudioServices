package com.disignstudio.project.server;

import com.codahale.metrics.ConsoleReporter;
import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.common.guice.DatabaseModule;
import com.disignstudio.common.guice.MongoModule;
import com.disignstudio.project.api.*;
import com.disignstudio.web.api.OpsAPI;
import com.disignstudio.web.api.OpsModule;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public class ProjectServicesServer extends Application<ConfigurationProvider> {

    public static void main(String[] args) throws Exception {
        System.out.println("main phase begins");
        new ProjectServicesServer().run(args);
    }

    @Override
    public String getName() {
        return "project-services";
    }

    @Override
    public void initialize(Bootstrap<ConfigurationProvider> bootstrap) {

        bootstrap.addBundle(new AssetsBundle());
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(bootstrap.getMetricRegistry()).build();
        //reporter.start(10, TimeUnit.SECONDS);
    }

    @Override
    public void run(ConfigurationProvider configuration, Environment env) {
        System.out.println("Run phase begins");
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                env.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        List<Module> injectorModules = Lists.newArrayList(new OpsModule(configuration), new MongoModule(configuration), new DatabaseModule(configuration), new ProjectServicesServerModule(configuration));
        Injector injector = Guice.createInjector(injectorModules);
        env.jersey().register(injector.getInstance(OpsAPI.class));
        env.jersey().register(injector.getInstance(ProjectsCRUDAPI.class));
        env.jersey().register(injector.getInstance(MobileContentAPI.class));
        env.jersey().register(injector.getInstance(ProjectsMetadataAPI.class));
        env.jersey().register(injector.getInstance(UsersAPI.class));
        env.jersey().register(injector.getInstance(AdminAPI.class));
        env.jersey().register(injector.getInstance(ProjectsAPI.class));
        env.jersey().register(injector.getInstance(StatsAPI.class));
        env.jersey().register(injector.getInstance(RedirectAPI.class));
        System.out.println("Run phase finish");
    }
}
