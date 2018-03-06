package com.example.config;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.core.env.Environment;

/**
 * Created by tomcollings on 3/6/18.
 */
public class CustomConfigClientProperties extends ConfigClientProperties {

    public CustomConfigClientProperties(Environment environment) {
        super (environment);
    }

    private String fileNames;

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public CustomConfigClientProperties override(
            org.springframework.core.env.Environment environment) {
        CustomConfigClientProperties override = new CustomConfigClientProperties(environment);
        BeanUtils.copyProperties(this, override);
        override.setName(
                environment.resolvePlaceholders("${" + ConfigClientProperties.PREFIX
                        + ".name:${spring.application.name:application}}"));
        if (environment.containsProperty(ConfigClientProperties.PREFIX + ".profile")) {
            override.setProfile(
                    environment.getProperty(ConfigClientProperties.PREFIX + ".profile"));
        }
        if (environment.containsProperty(ConfigClientProperties.PREFIX + ".label")) {
            override.setLabel(
                    environment.getProperty(ConfigClientProperties.PREFIX + ".label"));
        }
        return override;
    }

}
