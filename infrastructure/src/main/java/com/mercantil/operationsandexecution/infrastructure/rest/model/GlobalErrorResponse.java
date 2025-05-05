package com.mercantil.operationsandexecution.infrastructure.rest.model;


import com.mercantil.operationsandexecution.domain.customtime.TimeHandler;
import com.mercantil.operationsandexecution.domain.exception.ConstantException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalErrorResponse {
    private String statusCode;
    private String status;
    private String message;
    private ErrorData data;
    private String timestamp;
    private String transactionId;

    private static final String ERROR = "ERROR";

    public static GlobalErrorResponse create(ConstantException exception, String transactionId, String classEx){
        return new GlobalErrorResponse(String.valueOf(exception.getHttpStatus()),
                ERROR,
                exception.getMessage(),
                buildErrorData(exception, classEx),
                TimeHandler.currentDate(),
                transactionId);
    }

    private static ErrorData buildErrorData(ConstantException exception, String classEx){
        return new ErrorData(exception, classEx);
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorData {
        private ErrorDetail errorDetails;

        public ErrorData(ConstantException exception, String classEx){
            this.errorDetails = new ErrorDetail(HttpStatus.valueOf(exception.getHttpStatus()).getReasonPhrase(),
                    new Field(exception.getMessage(), classEx));
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorDetail {
        private String code;
        private Field fields;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Field {
        private String exceptionMessage;
        private String exceptionType;
    }
}
