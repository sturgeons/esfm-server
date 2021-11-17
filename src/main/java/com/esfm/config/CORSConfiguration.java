package com.esfm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
        super.addCorsMappings(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("file:/Users/yaoxin/");
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/").resourceChain(false);
//        registry.addResourceHandler("/druid/**").addResourceLocations("classpath:/support/http.resources/").resourceChain(false);
        registry.addResourceHandler("/p/**").addResourceLocations("file:/root/p/");
//        registry.addResourceHandler("/p/**").addResourceLocations("file:/Users/yaoxin/Documents/App/zf/zf_web/dist/");
        super.addResourceHandlers(registry);
    }
}
