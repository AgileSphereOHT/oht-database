package uk.doh.oht.database.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by peterwhitehead on 04/05/2017.
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "rest.connection")
    public HttpComponentsClientHttpRequestFactory restHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(restHttpRequestFactory());
    }
}

