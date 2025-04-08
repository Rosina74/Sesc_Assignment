package com.sesc.unistudycircle.student_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    @LoadBalanced
//    public WebClient.Builder webClientBuilder(){
//        return WebClient.builder();
//    }

    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }
}
