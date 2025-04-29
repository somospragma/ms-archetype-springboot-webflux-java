package com.mercantil.operationsandexecution.loansanddeposits.domain.constants.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonConstants {

    /* Constantes de codigo status */
    public static final String CODE_STATUS_200 = "200";
    public static final String CODE_STATUS_204 = "204";
    public static final String CODE_STATUS_400 = "400";
    public static final String CODE_STATUS_401 = "401";
    public static final String CODE_STATUS_403 = "403";
    public static final String CODE_STATUS_404 = "404";
    public static final String CODE_STATUS_408 = "408";
    public static final String CODE_STATUS_409 = "409";
    public static final String CODE_STATUS_500 = "500";
    public static final String CODE_STATUS_501 = "501";

    /* Constantes de levelLogs*/
    public static final String LOG_INFO = "INFO";
    public static final String LOG_WARNING = "WARN";
    public static final String LOG_ERROR = "ERROR";
    public static final String LOG_SUCCESS = "INFO";
    public static final String LOG_DEBUG = "DEBUG";

    /* Constantes Extras de utilidad */
    public static final String UTILITY_NAME_TABLE_LOAN_SQL = "loan";
    public static final String UTILITY_NAME_TABLE_LOAN_NO_SQL = "trace_loans";

    /* Constantes de Package */
    public static final String PACKAGE_PROJECT_ROOT = "com.mercantil.operationsandexecution";
    public static final String PACKAGE_ENTITY_MANAGERS_ENTITIES = "com.mercantil.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities";
    public static final String PACKAGE_INFRASTRUCTURE_CONTROLLERS = "com.mercantil.operationsandexecution.loansanddeposits.infrastructure.controllers";
    public static final String PACKAGE_INFRASTRUCTURE = "com.mercantil.operationsandexecution.loansanddeposits.infrastructure";

}
