package com.tr.spring;

import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Import(InMemoryDBAppConfiguration.class)
public class RestConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        
//    }
}
