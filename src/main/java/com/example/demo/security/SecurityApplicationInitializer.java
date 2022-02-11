package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

public class SecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

        @Override
        protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
            insertFilters(servletContext, new MultipartFilter());
        }

        @Bean(name = "multipartResolver")
        public CommonsMultipartResolver multipartResolver() {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
            multipartResolver.setMaxUploadSize(100000);
            return multipartResolver;
        }
    }
