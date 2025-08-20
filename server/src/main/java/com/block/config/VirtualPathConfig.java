package com.block.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class VirtualPathConfig implements WebMvcConfigurer {
    // 文件在硬盘中的路径
    @Value("${files.uploadFilePath}")
    private String uploadFilePath;
    // 虚拟访问的路径
    @Value("${files.virtualUploadPath}")
    private String virtualUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(virtualUploadPath+"**").addResourceLocations("file:"+System.getProperty("user.home")+uploadFilePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(virtualUploadPath+"**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600 * 24);
    }
}
