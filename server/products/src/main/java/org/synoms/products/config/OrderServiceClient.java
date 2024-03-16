package org.synoms.products.config;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.synoms.client.orders.RatingDTO;

import java.util.List;

public class OrderServiceClient {

    private final String ORDER_SERVICE_URL;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    public OrderServiceClient(Environment environment, RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.ORDER_SERVICE_URL = environment.getProperty("order.service.url");
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public List<RatingDTO> getAllRatingsByProductId(String productId){


        return null;
    }



}
