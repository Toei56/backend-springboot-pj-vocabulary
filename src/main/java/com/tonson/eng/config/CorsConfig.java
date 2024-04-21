package com.tonson.eng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**"); //เป็นการปลดล็อค cors ตาม Default
//        registry.addMapping("/path").allowedMethods("method", "method", "method", "method");
//        registry.addMapping("/**/*").allowedMethods("*"); ยอมรับทุก path ทุก methods
//        registry.addMapping("/**"); ยอมรับทุก path ทุก methods ยกเว้น delete
        registry.addMapping("/vocabulary/*").allowedMethods("*"); //เป็นการปลดล็อคทุก method ใน class vocabulary (สามารถใช้ method DELETE, POST ได้)
        // registry.addMapping("/**");
    }
}
