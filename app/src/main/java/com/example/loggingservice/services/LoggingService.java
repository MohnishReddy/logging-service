package com.example.loggingservice.services;

import com.example.loggingservice.enums.LogType;
import com.example.loggingservice.models.responses.GetLogsResponseModel;

import java.util.Date;

public interface LoggingService {
    String addLog(String tag, String message, LogType logType, Date timestamp, String createdBy) throws Exception;
    GetLogsResponseModel getLogById(String logId) throws Exception;
}
