package com.hvn.velocity.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hvn.velocity.session.Cart;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence.properties" })
@ComponentScan(basePackages = { "com.hvn.velocity" })
public class SpringDataConfig {
	
	@Autowired
	private Environment env;
	
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
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getProperties());
		return sessionFactory;
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
	    dataSource.setUrl(env.getProperty("hibernate.connection.url"));
	    dataSource.setUsername(env.getProperty("hibernate.connection.username"));
	    dataSource.setPassword(env.getProperty("hibernate.connection.password"));
	    return dataSource;
	}

	public Properties getProperties() {
		final String[] props = { "hibernate.dialect", "hibernate.c3p0.min_size",
				"hibernate.c3p0.max_size", "hibernate.c3p0.timeout",
				"hibernate.c3p0.max_statements", "hibernate.c3p0.idle_test_period" };
		Properties properties = new Properties();
		for (String prop : props) {
			properties.setProperty(prop, env.getProperty(prop));
		}
		return properties;
	}
	
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Cart shoppingCart() {
		return new Cart();
	}
	
}
