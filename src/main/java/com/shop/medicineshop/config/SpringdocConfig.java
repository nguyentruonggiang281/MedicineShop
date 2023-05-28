package com.shop.medicineshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@OpenAPIDefinition
@Configuration
public class SpringdocConfig {

    @Bean
    public OpenAPI baseOpenAPI() throws IOException {
        return new OpenAPI()
                .info(new Info().title("Spring Doc").version("1.0.0").description("Spring doc"));
    }

    @Bean
    public GroupedOpenApi authenticationApi(){
        String [] paths = {"/api/v1/products/**"};
        return GroupedOpenApi.builder()
                .group("Product")
                .pathsToMatch(paths)
                .build();
    }
    @Bean
    public GroupedOpenApi postApi(){
        String [] paths = {"/api/v1/categories/**"};
        return GroupedOpenApi.builder()
                .group("Categories")
                .pathsToMatch(paths)
                .build();
    }
//    @Bean
//    public GroupedOpenApi image(){
//        String [] paths = {"/images/**"};
//        return GroupedOpenApi.builder()
//                .group("image")
//                .pathsToMatch(paths)
//                .build();
//    }
}