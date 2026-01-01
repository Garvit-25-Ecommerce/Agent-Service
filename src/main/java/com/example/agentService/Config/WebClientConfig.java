package com.example.agentService.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${cart.service.url}")
    private String cartServiceUrl;


    @Bean
    public WebClient productClient() {
        return WebClient.builder().baseUrl(productServiceUrl)
                .build();

    }

    @Bean
    public WebClient cartClient() {
        return WebClient.builder()
                .baseUrl(cartServiceUrl)
                .build();
    }
}
