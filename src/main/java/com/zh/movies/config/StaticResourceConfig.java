package com.zh.movies.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    private static final String IMAGE_DIR = "D:/Movies-Videos-Images/images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /images/** to local folder
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///" + IMAGE_DIR)
                .setCachePeriod(3600);
    }
}

