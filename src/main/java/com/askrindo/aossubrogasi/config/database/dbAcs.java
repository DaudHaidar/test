package com.askrindo.aossubrogasi.config.database;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "acsEntityManagerFactory",
        transactionManagerRef = "acsTransactionManager", basePackages = {
        "com.askrindo.aossubrogasi.repository.acs"})

public class dbAcs {
    @Autowired
    Environment env;

    @Bean(name = "acsDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.acs.datasource.driverClassName"))
                .url(env.getProperty("spring.acs.datasource.url"))
                .username(env.getProperty("spring.acs.datasource.username"))
                .password(env.getProperty("spring.acs.datasource.password"))
                .build();
    }

    @Bean(name = "acsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("acsDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.askrindo.aossubrogasi.entity.acs").persistenceUnit("acs").build();
    }

    @Bean(name = "acsTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("acsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }   
}
