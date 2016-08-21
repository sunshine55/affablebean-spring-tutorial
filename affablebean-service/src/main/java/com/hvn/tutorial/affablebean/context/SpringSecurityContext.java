package com.hvn.tutorial.affablebean.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@EnableWebSecurity
@ImportResource({ "classpath:spring-security.xml" })
public class SpringSecurityContext {
	
	@Bean
	public DelegatingFilterProxy springSecurityFilterChain() {
		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
		return filterProxy;
	}
	
}
