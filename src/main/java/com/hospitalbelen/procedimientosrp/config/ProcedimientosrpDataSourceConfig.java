    package com.hospitalbelen.procedimientosrp.config;


    import javax.sql.DataSource;

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

    import jakarta.persistence.EntityManagerFactory;

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(
            entityManagerFactoryRef = "procedimientosrpEntityManagerFactory",
            transactionManagerRef = "procedimientosrpTransactionManager",
            basePackages = { "com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository" }
    )
    public class ProcedimientosrpDataSourceConfig {

        @Bean(name="procedimientosrpDataSource")
        @ConfigurationProperties(prefix="spring.datasource.procedimientosrp")
        public DataSource procedimientosrpDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean(name = "procedimientosrpEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean procedimientosrpEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                           @Qualifier("procedimientosrpDataSource") DataSource procedimientosrpDataSource) {
            return builder
                    .dataSource(procedimientosrpDataSource)
                    .packages("com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity")
                    .build();
        }

        @Bean(name = "procedimientosrpTransactionManager")
        public PlatformTransactionManager procedimientosrpTransactionManager(
                @Qualifier("procedimientosrpEntityManagerFactory") EntityManagerFactory procedimientosrpEntityManagerFactory) {
            return new JpaTransactionManager(procedimientosrpEntityManagerFactory);
        }

    }