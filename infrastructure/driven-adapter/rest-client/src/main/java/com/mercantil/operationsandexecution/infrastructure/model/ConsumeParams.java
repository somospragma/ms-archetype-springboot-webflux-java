package com.mercantil.operationsandexecution.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsumeParams {
    private String baseUrl;
    private String path;

    public String getCompleteUri(){
        return baseUrl.concat(path);
    }
}
