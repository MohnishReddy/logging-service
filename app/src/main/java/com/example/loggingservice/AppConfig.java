package com.example.loggingservice;

import org.springframework.context.annotation.Configuration;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void onStartup(ServletContext servletContext) {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CorsFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
