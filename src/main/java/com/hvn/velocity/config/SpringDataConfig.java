package com.hvn.velocity.config;

import com.hvn.velocity.session.Cart;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
@EnableJpaRepositories("com.hvn.velocity.repository")
class SpringDataConfig {

    @Resource
    private Environment env;

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan(new String[]{"com.hvn.velocity.domain"});
        emf.setDataSource(getDataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(getProperties());
        return emf;
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

    @Bean
    public Properties getProperties() {
        final String[] props = {"hibernate.dialect", "hibernate.c3p0.min_size",
                "hibernate.c3p0.max_size", "hibernate.c3p0.timeout",
                "hibernate.c3p0.max_statements", "hibernate.c3p0.idle_test_period"};
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
