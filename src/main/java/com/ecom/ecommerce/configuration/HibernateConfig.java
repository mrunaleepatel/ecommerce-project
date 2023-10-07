package com.ecom.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.ecom.ecommerce.repository")
@EnableTransactionManagement
public class HibernateConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String DATABASE_DRIVER;
    @Value("${spring.datasource.url}")
    private String DATABASE_URL;
    @Value("${spring.datasource.username}")
    private String DATABASE_USERNAME;
    @Value("${spring.datasource.password}")
    private String DATABASE_PASSWORD;

    @Value("${spring.jpa.hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ecommercewebsite");
        dataSource.setUsername("root");
        dataSource.setPassword("ecommercetwo");
        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        return hibernateProperties;
    }
}
