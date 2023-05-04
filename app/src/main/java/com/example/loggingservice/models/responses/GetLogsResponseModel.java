package com.example.loggingservice.models.responses;

import com.example.loggingservice.models.logs.Log;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetLogsResponseModel {
    private List<Log> logList;
    private String errMessage;

    public GetLogsResponseModel() {
        logList = new ArrayList<>();
    }
}
