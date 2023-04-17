package com.shop.medicineshop.config;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Value("drn7nawnc")
    String cloudName;

    @Value("569136995295752")
    String apiKey;

    @Value("wfpIuQNBWgSWhC1KW4d1WFdA3jE")
    String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
        return cloudinary;
    }
}
