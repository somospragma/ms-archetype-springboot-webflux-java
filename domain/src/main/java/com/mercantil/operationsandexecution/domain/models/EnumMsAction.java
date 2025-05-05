package com.mercantil.operationsandexecution.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumMsAction {
    RETRIEVE_USER_BY_ID("Consulta de usuario por id o username"),
    NOT_IDENTIFIED("Servicio ejecutado no capturado"),;

    private final String logString;
}
