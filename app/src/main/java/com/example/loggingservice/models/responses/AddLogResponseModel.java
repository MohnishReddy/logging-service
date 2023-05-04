package com.example.loggingservice.models.responses;

import lombok.Data;

@Data
public class AddLogResponseModel {
    private String logId;
    private String errMessage;
}
