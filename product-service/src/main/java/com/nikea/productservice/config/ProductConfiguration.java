package com.nikea.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;

import javax.sql.DataSource;

@Configuration
public class ProductConfiguration {
    @Bean
    public DefaultLockRepository DefaultLockRepository(DataSource dataSource){
        DefaultLockRepository repository = new DefaultLockRepository(dataSource);
        repository.setTimeToLive(60 * 1000);
        return repository;
    }

    @Bean
    public JdbcLockRegistry jdbcLockRegistry(LockRepository lockRepository){
        return new JdbcLockRegistry(lockRepository);
    }
}
