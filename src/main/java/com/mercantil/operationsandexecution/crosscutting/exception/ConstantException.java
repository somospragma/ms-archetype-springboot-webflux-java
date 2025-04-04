package com.mercantil.operationsandexecution.crosscutting.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantException {

    LOAN_AMOUNT_EXCEEDS_LIMIT(407, "CODE401", "Monto de prestamo excede el limite."),
    ERROR_EXTERNAL_SERVICE_CONSUME(500, "CODE500", "Error consumiendo API externa"),
    INTERNAL_SERVER_ERROR(500, "CODE-500", "Error interno del servidor.");

    private final Integer httpStatus;
    private final String errorCode;
    private final String message;
}
