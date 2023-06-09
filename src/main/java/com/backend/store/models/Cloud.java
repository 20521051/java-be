package com.backend.store.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class Cloud {
    @Value("${CLOUD_NAME}")
    private String cloudName;

    @Value("${CLOUDINARY_API_KEY}")
    private String apiKey;

    @Value("${CLOUDINARY_API_SECRET}")
    private String apiSecret;

    private Boolean secure = true;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", secure));
    }
}
