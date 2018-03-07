package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class PropertyLoader
{
    @Autowired
    Environment environment;

    @Bean
    public CustomConfigClientProperties configClientProperties() {
        CustomConfigClientProperties client = new CustomConfigClientProperties(this.environment);
        return client;
    }


    @Bean("customPropertyLocator")
    public CustomConfigServicePropertyLocator configServicePropertySourceLocator() {
        CustomConfigClientProperties clientProperties = configClientProperties();
        CustomConfigServicePropertyLocator configServicePropertySourceLocator =  new CustomConfigServicePropertyLocator(clientProperties);
        return configServicePropertySourceLocator;
    }

}
