package com.mercantil.operationsandexecution.crosscutting.infraestructure.entrypoints.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mercantil.operationsandexecution.crosscutting.domain.customtime.TimeHandler;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseSuccessResponse<T> {
    private String statusCode;
    private String status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private String timestamp;
    private String transactionId;

    // TODO: 26/01/25 Create response success contants. Maybe using a global interface to unify enum structure.
    public static <T> BaseSuccessResponse<T> createSuccessResponse(String statusCode, String msgId){
        BaseSuccessResponse<T> response = new BaseSuccessResponse<>();
        response.setStatusCode(statusCode);
        response.setStatus("SUCCESS");
        response.setMessage("Creación exitosa");
        response.setTransactionId(msgId);
        response.setTimestamp(TimeHandler.currentDate());

        return response;
    }

    public BaseSuccessResponse<T> setBody(T data){
        this.data = data;
        return this;
    }
}
