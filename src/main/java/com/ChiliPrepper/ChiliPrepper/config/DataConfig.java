package com.ChiliPrepper.ChiliPrepper.config;

/**
 * Created by Andreas on 15.02.2017.
 */

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration   //Let Spring know this is a configuration class
@EnableJpaRepositories(basePackages = "com.ChiliPrepper.ChiliPrepper.dao")   //Enable JPA-Repositories
@PropertySource("app.properties")    //Let Spring know where to look for data properties
@EnableTransactionManagement
public class DataConfig {

    @Autowired
    private Environment env;  //The content of app.properties is loaded into the Environment

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setDataSource(dataSource());                                        //calls method below
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(env.getProperty("ChiliPrepper.entity.package"));
        factory.setJpaProperties(getHibernateProperties());                         //calls method below

        return factory;
    }

    @Bean
    public DataSource dataSource() {                  //All of these properties are defined in "app.properties" in the static directory
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("ChiliPrepper.db.driver"));
        ds.setUrl(env.getProperty("ChiliPrepper.db.url"));
        ds.setUsername(env.getProperty("ChiliPrepper.db.username"));
        ds.setPassword(env.getProperty("ChiliPrepper.db.password"));
        return ds;
    }

    private Properties getHibernateProperties() {    //All of these properties are defined in "app.properties" in the static directory
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.implicit_naming_strategy",env.getProperty("hibernate.implicit_naming_strategy"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.cache.provider_class", env.getProperty("hibernate.cache.provider_class"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
}