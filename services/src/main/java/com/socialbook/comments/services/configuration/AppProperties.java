package com.socialbook.comments.services.configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("app-properties")
public class AppProperties {
    @ConfigValue(value = "statistic-service.enabled", watch = true)
    private boolean statisticServiceEnabled;

    public boolean isStatisticServiceEnabled() {
        return statisticServiceEnabled;
    }

    public void setStatisticServiceEnabled(boolean statisticServiceEnabled) {
        this.statisticServiceEnabled = statisticServiceEnabled;
    }
}
