package com.huami.yuezhichao.restful.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("localhost")
//                .allowedMethods("*")
//                .allowedHeaders("*");
    }

    @Bean
    public IpInterceptor generateInterceptor(){
        return new IpInterceptor();
    }

    @Bean
    public RefererInterceptor generateRefererInterceptor(){
        return new RefererInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(generateRefererInterceptor()).addPathPatterns("/v2/**");
        registry.addInterceptor(generateInterceptor()).addPathPatterns("/user/**");
        super.addInterceptors(registry);
    }

}
