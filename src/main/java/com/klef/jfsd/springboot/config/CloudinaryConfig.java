package com.klef.jfsd.springboot.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    
    @Bean
    public Cloudinary cloudinary() {
        // Configure Cloudinary with the provided credentials
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dlgwz9tj9",  // Your Cloudinary cloud name
                "api_key", "917617438499172",  // Your Cloudinary API key
                "api_secret", "Q_jZY1t785E6cN9zLtrkq6TxOCc"  // Your Cloudinary API secret
        ));
    }
}
