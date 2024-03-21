package com.postech30.msusermanager.security.config;

import com.postech30.msusermanager.security.authentication.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/users/login", "/users"
    };

    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/users/test"
    };

    public static final String[] ENDPOINTS_CUSTOMER = {
            "/users/test/customer"
    };

    public static final String[] ENDPOINTS_ADMIN = {
            "/users/test/administrator"
    };

}
