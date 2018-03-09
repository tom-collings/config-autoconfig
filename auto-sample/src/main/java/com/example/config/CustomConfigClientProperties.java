package com.example.config;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.core.env.Environment;

/**
 * Created by tomcollings on 3/6/18.
 */
@ConfigurationProperties("com.example.config")
public class CustomConfigClientProperties  {

    public CustomConfigClientProperties(Environment environment) {

    }

    private String fileNames;

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }


}
