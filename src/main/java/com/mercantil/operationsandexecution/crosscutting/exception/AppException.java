package com.mercantil.operationsandexecution.crosscutting.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{
    private final ConstantException constantException;
    private final String optionalInfo;
    private static final String ADDITIONAL_INFO_NOT_REQUIRED = "NOT_REQUIRED";

    public AppException(ConstantException constantException){
        super(constantException.getMessage());
        this.constantException = constantException;
        this.optionalInfo = ADDITIONAL_INFO_NOT_REQUIRED;
    }

    public AppException(ConstantException constantException, String additionalInfo){
        super(constantException.getMessage());
        this.constantException = constantException;
        this.optionalInfo = additionalInfo;
    }
}
