package com.hvn.velocity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hvn.velocity.session.Cart;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.hvn.velocity" })
public class SpringDataConfig {
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(affableSessionFactory().getObject());
		return htm;
	}

	@Bean
	public LocalSessionFactoryBean affableSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setPackagesToScan(new String[] { "com.hvn.velocity.domain" });
		Resource resource = new ClassPathResource("hibernate.cfg.xml");
		sessionFactory.setConfigLocation(resource);
		return sessionFactory;
	}
	
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Cart shoppingCart() {
		return new Cart();
	}
	
}
