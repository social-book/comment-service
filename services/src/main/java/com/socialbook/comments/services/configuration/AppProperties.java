package com.socialbook.comments.services.configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("app-properties")
public class AppProperties {
    @ConfigValue(value = "statistic-service.enabled", watch = true)
    private boolean statisticServiceEnabled;

    @ConfigValue(value = "external-api.enabled", watch = true)
    private boolean externalApiEnabled;

    public boolean isStatisticServiceEnabled() {
        return statisticServiceEnabled;
    }

    public void setStatisticServiceEnabled(boolean statisticServiceEnabled) {
        this.statisticServiceEnabled = statisticServiceEnabled;
    }

    public boolean isExternalApiEnabled() {
        return externalApiEnabled;
    }

    public void setExternalApiEnabled(boolean externalApiEnabled) {
        this.externalApiEnabled = externalApiEnabled;
    }
}
