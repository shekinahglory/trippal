package com.apstream.jwtprep.configuration.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:///${user.home}/application-prod.properties")
public class PropertiesConfig {
}
