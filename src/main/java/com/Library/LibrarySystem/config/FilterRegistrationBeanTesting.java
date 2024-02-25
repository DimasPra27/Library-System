package com.Library.LibrarySystem.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class FilterRegistrationBeanTesting {
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestResponseLoggingFilter());
        registration.addUrlPatterns("/books");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("someFilter");
        registration.setOrder(1);
        return registration;
    }
}
