package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.adapter.sqlserver.config;

import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.time.Duration;

@Configuration
@EnableR2dbcRepositories
public class SqlServerConfig {
    public static final int INITIAL_SIZE = 12;
    public static final int MAX_SIZE = 15;
    public static final int MAX_IDLE_TIME = 30;

    @Bean
    public ConnectionFactory connectionFactory(SqlServerConnectionProperties sqlServerConnectionProperties) {
        // Crear la fábrica de conexiones para SQL Server
        MssqlConnectionConfiguration dbConfiguration = MssqlConnectionConfiguration.builder()
                .host(sqlServerConnectionProperties.getHost())
                .port(sqlServerConnectionProperties.getPort())
                .database(sqlServerConnectionProperties.getDbname())
                .username(sqlServerConnectionProperties.getUsername())
                .password(sqlServerConnectionProperties.getPassword())
                .build();


        // Configurar el pool de conexiones
        ConnectionPoolConfiguration poolConfig = ConnectionPoolConfiguration.builder()
                .connectionFactory(new MssqlConnectionFactory(dbConfiguration))
                .initialSize(INITIAL_SIZE)
                .maxSize(MAX_SIZE)
                .maxIdleTime(Duration.ofMinutes(MAX_IDLE_TIME))
                .validationQuery("SELECT 1") // Query para validar las conexiones
                .build();

        return new ConnectionPool(poolConfig);
    }
}
