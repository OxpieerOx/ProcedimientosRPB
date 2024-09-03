package com.hospitalbelen.procedimientosrp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

@Configuration
public class SecondaryDataSourceConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        final String jdbcUrl = "jdbc:sqlserver://host.docker.internal:1433;databaseName=SIGH;encrypt=false;trustServerCertificate=true;sslProtocol=TLSv1.2";
        final String username = "sa";
        final String password = "123456";

        final Class<?> driverClass = ClassUtils.resolveClassName(driverClassName, this.getClass().getClassLoader());
        final Driver driver = (Driver) ClassUtils.getConstructorIfAvailable(driverClass).newInstance();
        final DataSource dataSource = new SimpleDriverDataSource(driver, jdbcUrl, username, password);

        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return new NamedParameterJdbcTemplate(jdbcTemplate().getDataSource());
    }
}