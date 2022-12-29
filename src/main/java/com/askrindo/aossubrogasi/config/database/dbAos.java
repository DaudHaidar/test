package com.askrindo.aossubrogasi.config.database;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
import org.springframework.context.annotation.Primary;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "aosEntityManagerFactory",
        transactionManagerRef = "aosTransactionManager", basePackages = {
        "com.askrindo.aossubrogasi.repository.aos"})
@EntityScan("com.askrindo.aossubrogasi.repository")
public class dbAos {
    @Autowired
    Environment env;

    @Primary
    @Bean(name = "aosDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.aos.datasource.driverClassName"))
                .url(env.getProperty("spring.aos.datasource.url"))
                .username(env.getProperty("spring.aos.datasource.username"))
                .password(env.getProperty("spring.aos.datasource.password"))
                .build();
    }

    @Primary
    @Bean(name = "aosEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("aosDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.askrindo.aossubrogasi.entity.aos").persistenceUnit("aos").build();
    }

    @Primary
    @Bean(name = "aosTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("aosEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
                return new JpaTransactionManager(entityManagerFactory);
        
            }
}
