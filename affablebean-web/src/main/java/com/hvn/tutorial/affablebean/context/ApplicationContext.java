package com.hvn.tutorial.affablebean.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({
    "com.hvn.tutorial.affablebean.session",
    "com.hvn.tutorial.affablebean.context",
    "com.hvn.tutorial.affablebean.service",
    "com.hvn.tutorial.affablebean.web"
})
@EnableWebMvc
public class ApplicationContext extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
