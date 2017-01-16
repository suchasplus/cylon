package com.weibo.ad.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MySQLDSConfig {

    @Bean(name="ds1")
    @Qualifier("ds1")
    @ConfigurationProperties(prefix="mysqldb.datasource.ds1")
    public DataSource ds1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ds2")
    @Qualifier("ds2")
    @Primary
    @ConfigurationProperties(prefix="mysqldb.datasource.ds2")
    public DataSource ds2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="ds1jt")
    public JdbcTemplate ds1jdbctmpl(@Qualifier("ds1") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="ds2jt")
    public JdbcTemplate ds2jdbctmpl(@Qualifier("ds2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
