package org.example.springdbtest.db2.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(
        basePackages = "org.example.springdbtest.db2.repository", // Пакет репозиториев для первой БД
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager" )
@EnableTransactionManagement
public class SecondaryConfig {

        @Bean(name = "secondDataSource")
        @ConfigurationProperties("spring.datasource.second")
        public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
        }

        @Bean(name = "secondEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("secondDataSource") DataSource dataSource) {
        return builder
        .dataSource(dataSource)
        .packages("org.example.springdbtest.db2.entity") // Пакет сущностей для первой БД
        .persistenceUnit("second")
        .build();
        }

        @Bean(name = "secondTransactionManager")
        public PlatformTransactionManager secondTransactionManager(
        @Qualifier("secondEntityManagerFactory") EntityManagerFactory secondEntityManagerFactory) {
        return new JpaTransactionManager(secondEntityManagerFactory);
        }

}
