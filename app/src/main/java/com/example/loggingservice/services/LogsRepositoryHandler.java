package com.example.loggingservice.services;

import com.example.loggingservice.entities.Logs;

public interface LogsRepositoryHandler {
    String saveLog(Logs log);
    Logs getLogById(String logId);
}
