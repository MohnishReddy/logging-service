package com.example.loggingservice.services;

import com.example.loggingservice.entities.Logs;
import com.example.loggingservice.enums.LogEntityMapKey;
import com.example.loggingservice.enums.LogType;
import com.example.loggingservice.enums.SortOrder;

import java.util.Date;
import java.util.List;

public interface LogsRepositoryHandler {
    String saveLog(Logs log);
    Logs getLogById(String logId);
    List<Logs> getLogList(List<LogType> logTypeList, Date createdAfter, Date createdBefore, String tag, List<Boolean> trusted, List<Boolean> visible, int pageNumber, int limit, LogEntityMapKey sortBy, SortOrder sortOrder);
}
