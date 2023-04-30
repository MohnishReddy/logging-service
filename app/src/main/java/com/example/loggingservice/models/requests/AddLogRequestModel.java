package com.example.loggingservice.models.requests;

import lombok.Data;

@Data
public class AddLogRequestModel {
    private String tag;
    private String message;
    private String logType;
    private String timestamp;
    private String createdBy;
}
