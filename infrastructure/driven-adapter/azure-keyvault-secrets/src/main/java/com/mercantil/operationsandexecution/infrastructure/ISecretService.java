package com.mercantil.operationsandexecution.infrastructure;

public interface ISecretService {
    // TODO: 23/01/25 Evaluar posibilidad de usar genéricos o enviar por parámetro tipo de clase.
    String getSecret(String secretName);
}
