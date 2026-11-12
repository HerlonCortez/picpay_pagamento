package com.herlon.picpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient notificationRestClient(
            RestClient.Builder builder) {

        return builder
                .baseUrl("https://util.devi.tools/api/v1/notify")
                .build();
    }
}
