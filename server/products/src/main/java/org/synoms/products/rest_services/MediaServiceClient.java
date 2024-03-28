package org.synoms.products.rest_services;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class MediaServiceClient {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final String MEDIA_SERVICE_URL;

    public MediaServiceClient(RestTemplate restTemplate, HttpHeaders httpHeaders, Environment environment) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        this.MEDIA_SERVICE_URL=environment.getProperty("media.service.url");
    }

    public String getCategoryImage(String id){
        return null;
    }
}
