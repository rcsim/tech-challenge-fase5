package com.postech30.gateway;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterConfig {
    @Bean
    public FilterRegistrationBean<GatewaySecurityFilter> securityFilterRegistration() {
        FilterRegistrationBean<GatewaySecurityFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new GatewaySecurityFilter(new GatewayTokenService()));
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
