package com.example.loggingservice.models.requests;

import com.example.loggingservice.enums.LogType;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateLogRequestModel {
    private String tag;
    private Boolean trusted;
    private Boolean visible;
}
