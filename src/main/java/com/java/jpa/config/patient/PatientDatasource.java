package com.java.jpa.config.patient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "mongodbTransactionManager", entityManagerFactoryRef = "mongodbEmFactory"
                        , basePackages = "{com.java.jpa.repository.patient}")
public class PatientDatasource {

    @Primary
    @Bean
    @ConfigurationProperties("spring.data.mongodb")
    public DataSourceProperties mongodbProperties(){
        return new DataSourceProperties();
    }

    /**
     * in mutiDatasource we will have multiple DataSourceProperties object. Below method we need
     * DataSourceProperties bean related to mongoDB. So we need to use @Qualifier annotaion
     * @return
     */
    @Primary
    @Bean
    public DataSource mongodbDataSource(@Qualifier("mongodbProperties") DataSourceProperties dp){
        return dp.initializeDataSourceBuilder().build();
    }

    /**
     * we have to tell EntityManager to handle which all entity.
     * That is why while creating EntityManagerFactory we have to provide
     * packge name which contains list of entity which is related to mongodb
     */
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean mongodbEmFactory(
        @Qualifier("mongodbDataSource") DataSource dataSource
        ,EntityManagerFactoryBuilder builder){
        return builder.dataSource(dataSource).packages("com.java.jpa.model.patient").build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager mongodbTransactionManager(
        @Qualifier("mongodbEmFactory") EntityManagerFactory em){
        return new JpaTransactionManager(em);
    }



}
