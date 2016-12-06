package com.disignstudio.supplier.server;

import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.common.guice.DatabaseModule;
import com.disignstudio.supplier.server.api.SupplierServerCRUDAPI;
import com.disignstudio.web.api.OpsAPI;
import com.disignstudio.web.api.OpsModule;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.List;

/**
 * Created by ohadbenporat on 1/9/16.
 */
public class SupplierServicesServer extends Application<ConfigurationProvider> {

    public static void main(String[] args) throws Exception {
        new SupplierServicesServer().run(args);
    }

    @Override
    public String getName() {
        return "supplier-services";
    }

    @Override
    public void initialize(Bootstrap<ConfigurationProvider> bootstrap) {

    }

    @Override
    public void run(ConfigurationProvider configuration, Environment env) {

        List<Module> injectorModules = Lists.newArrayList(new OpsModule(configuration), new DatabaseModule(configuration), new SupplierServicesServerModule());
        Injector injector = Guice.createInjector(injectorModules);
        env.jersey().register(injector.getInstance(SupplierServerCRUDAPI.class));
        env.jersey().register(injector.getInstance(OpsAPI.class));
    }
}
