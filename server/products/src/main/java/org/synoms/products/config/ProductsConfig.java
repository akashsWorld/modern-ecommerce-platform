package org.synoms.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.synoms.products.rest_services.MediaServiceClient;
import org.synoms.products.rest_services.OrderServiceClient;
import org.synoms.products.util_services.SortOnObject;
import org.synoms.products.util_services.UtilServices;

@Configuration
public class ProductsConfig {

    @Bean
    protected SortOnObject sortOnObject(){return new SortOnObject();}

    @Bean
    protected RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders(){
        HttpHeaders httpHeadersObj = new HttpHeaders();
        httpHeadersObj.setContentType(MediaType.APPLICATION_JSON);
        return httpHeadersObj;
    }


    @Bean
    protected OrderServiceClient orderServiceClient(RestTemplate restTemplate, Environment environment , HttpHeaders httpHeaders){
        return new OrderServiceClient(environment,restTemplate,httpHeaders);
    }

    @Bean
    protected MediaServiceClient mediaServiceClient(RestTemplate restTemplate,HttpHeaders httpHeaders,Environment environment){
        return new MediaServiceClient(restTemplate,httpHeaders,environment);
    }

}
