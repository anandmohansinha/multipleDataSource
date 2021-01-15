package com.java.jpa.config.doctor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "doctorEmFactory",transactionManagerRef = "doctorTransactionManager"
                        , basePackages = "{com.java.jpa.repository.doctor}")
public class DoctorDataSource {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties doctorDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource doctorDataSource(@Qualifier("doctorDataSourceProperties")DataSourceProperties dp){
       return dp.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean doctorEmFactory(
            @Qualifier("doctorDataSource") DataSource ds
            , EntityManagerFactoryBuilder builder){
        return builder.dataSource(ds).packages("com.java.jpa.model.doctor").build();
    }

    @Bean
    public PlatformTransactionManager doctorTransactionManager(
            @Qualifier("doctorEmFactory")EntityManagerFactory em
            ){
        return new JpaTransactionManager(em);
    }




}
