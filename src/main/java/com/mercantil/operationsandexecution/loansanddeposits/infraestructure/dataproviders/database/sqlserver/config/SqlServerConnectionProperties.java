package com.mercantil.operationsandexecution.loansanddeposits.infraestructure.dataproviders.database.sqlserver.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SqlServerConnectionProperties {
    private String dbClusterIdentifier;
    private String password;
    private String dbname;
    private String engine;
    private Integer port;
    private String host;
    private String username;
}
