package com.retotecnico.ApiBackend.model;

import lombok.Data;

@Data
public class ErrorModel {
    private String errorCode;
    private String errorType;
    private String errorDescription;
}
