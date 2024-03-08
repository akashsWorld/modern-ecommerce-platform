package org.synoms.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.synoms.products.util.UtilServices;

@Configuration
public class ProductsConfig {


    @Bean
    protected UtilServices utilServices(){
        return new UtilServices();
    }

}
