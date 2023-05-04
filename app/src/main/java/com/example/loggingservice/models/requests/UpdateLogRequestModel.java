package com.example.loggingservice.models.requests;

import lombok.Data;

@Data
public class UpdateLogRequestModel {
    private String tag;
    private Boolean trusted;
    private Boolean visible;
}
