package com.disignstudio.common.guice;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.disignstudio.common.configuration.ConfigurationProvider;
import com.google.inject.Binder;
import com.google.inject.Module;

import java.util.concurrent.TimeUnit;

/**
 * Created by ohadbenporat on 4/4/16.
 */
public class MetricsModule implements Module {

    private ConfigurationProvider configuration;

    public MetricsModule(ConfigurationProvider configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure(Binder binder) {

        MetricRegistry metrics = new MetricRegistry();
        /*ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10, TimeUnit.SECONDS);*/

        binder.bind(MetricRegistry.class).toInstance(metrics);
    }
}
