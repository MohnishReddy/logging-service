package com.example.loggingservice.services;

import com.example.loggingservice.entities.Logs;
import com.example.loggingservice.enums.LogType;
import com.example.loggingservice.models.logs.Log;
import com.example.loggingservice.models.responses.GetLogsResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LoggingServiceImpl implements LoggingService{

    @Autowired
    private LogsRepositoryHandler logsRepositoryHandler;

    public String addLog(String tag, String message, LogType logType, Date timestamp, String createdBy) throws Exception {
        try {
            return logsRepositoryHandler.saveLog(new Logs(tag, message, logType, timestamp, createdBy));
        } catch (Exception e) {
            throw new Exception("Couldn't save log Error: "+e.getMessage());
        }
    }

    public GetLogsResponseModel getLogById(String logId) {
        GetLogsResponseModel responseModel = new GetLogsResponseModel();
        List<Log> logList = responseModel.getLogList();

        Logs log = logsRepositoryHandler.getLogById(logId);

        Log logModel = Log.getLogModel(log.getLogType());
        logModel.mapDataToModel(log.getDataMap());

        logList.add(logModel);
        responseModel.setLogList(logList);

        return responseModel;
    }

}
