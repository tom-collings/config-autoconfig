package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
public class PropertyLoader
{
    @Autowired
    Environment environment;

    @Bean
    public ConfigClientProperties configClientProperties() {
        ConfigClientProperties client = new ConfigClientProperties(this.environment);
        return client;
    }

    @Bean
    @Profile("!cloud")
    public CustomConfigClientProperties customConfigClientProperties() {
        CustomConfigClientProperties client = new CustomConfigClientProperties(this.environment);
        return client;
    }


    @Bean("customPropertyLocator")
    public CustomConfigServicePropertyLocator configServicePropertySourceLocator() {
        CustomConfigClientProperties clientProperties = customConfigClientProperties();
        ConfigClientProperties cliProps = configClientProperties();
        CustomConfigServicePropertyLocator configServicePropertySourceLocator =  new CustomConfigServicePropertyLocator(clientProperties, cliProps);
        return configServicePropertySourceLocator;
    }

}
