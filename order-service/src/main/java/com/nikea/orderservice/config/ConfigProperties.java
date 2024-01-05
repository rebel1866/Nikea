package com.nikea.orderservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "config")
@Configuration
@Getter
@Setter
public class ConfigProperties {
    private String testValue;
    private String anotherTestValue;
}
