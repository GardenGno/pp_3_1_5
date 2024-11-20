package com.dyachenko.spring.rest.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.dyachenko.spring.rest")
public class MyConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();

    }
}
