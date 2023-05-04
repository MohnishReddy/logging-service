package com.example.loggingservice.services;

import com.example.loggingservice.entities.Logs;
import com.example.loggingservice.enums.LogEntityMapKey;
import com.example.loggingservice.enums.LogType;
import com.example.loggingservice.enums.SortOrder;
import com.example.loggingservice.models.logs.Log;
import com.example.loggingservice.models.requests.AddLogRequestModel;
import com.example.loggingservice.models.requests.UpdateLogRequestModel;
import com.example.loggingservice.models.responses.AddLogResponseModel;
import com.example.loggingservice.models.responses.GetLogsResponseModel;
import com.example.loggingservice.models.responses.UpdateLogResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoggingServiceImpl implements LoggingService{

    @Autowired
    private LogsRepositoryHandler logsRepositoryHandler;

    public AddLogResponseModel addLog(AddLogRequestModel logRequest) throws Exception {
        AddLogResponseModel responseModel = new AddLogResponseModel();

        Logs log;
        try {
            String tag = logRequest.getTag();
            String message = logRequest.getMessage();
            LogType logType = LogType.valueOf(logRequest.getLogType());
            Date timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(logRequest.getTimestamp());
            String createdBy = logRequest.getCreatedBy();
            log = new Logs(tag, message, logType, timestamp, createdBy);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        try {
            String logId = logsRepositoryHandler.saveLog(log);
            responseModel.setLogId(logId);
        } catch (Exception e) {
            throw new Exception("Couldn't save log Error: "+e.getMessage());
        }

        return responseModel;
    }

    public UpdateLogResponseModel updateLog(String logId, UpdateLogRequestModel updateReq) {
        UpdateLogResponseModel responseModel = new UpdateLogResponseModel();

        Logs log = logsRepositoryHandler.getLogById(logId);
        if(log == null)
            throw new NoSuchElementException("No such log exists");

        if(updateReq.getTag() != null)
            log.setTag(updateReq.getTag());
        if(updateReq.getTrusted() != null)
            log.setTrusted(updateReq.getTrusted());
        if(updateReq.getVisible() != null)
            log.setVisible(updateReq.getVisible());

        logsRepositoryHandler.saveLog(log);
        return responseModel;
    }

    public GetLogsResponseModel getLogById(String logId) {
        GetLogsResponseModel responseModel = new GetLogsResponseModel();
        List<Log> logList = responseModel.getLogList();

        Logs log = logsRepositoryHandler.getLogById(logId);
        if(log == null)
            throw new NoSuchElementException("No such log exists");

        logList.add(entityToModel(log));
        responseModel.setLogList(logList);

        return responseModel;
    }

    public GetLogsResponseModel getFilteredListOfLogs(String[] logTypeList, String createdAfter, String createdBefore, String tag, String[] trusted, String[] visible, String pageNumber, String limit, String sortBy, String sortOrder) throws Exception {
        GetLogsResponseModel responseModel = new GetLogsResponseModel();

        List<LogType> logTypeListFinal = new ArrayList<>();
        Date createdAfterFinal = new Date(0);
        Date createdBeforeFinal = new Date();
        List<Boolean> trustedFinal = new ArrayList<>();
        List<Boolean> visibleFinal = new ArrayList<>();
        int pageNumberFinal = 0;
        int limitFinal = 1000;
        LogEntityMapKey sortByFinal = LogEntityMapKey.CREATED_AT;
        SortOrder sortOrderFinal = SortOrder.ASC;
        try {
            if(logTypeList == null) {
                logTypeListFinal.add(LogType.ADMIN);
                logTypeListFinal.add(LogType.INFO);
                logTypeListFinal.add(LogType.INFO);
                logTypeListFinal.add(LogType.DEBUG);
                logTypeListFinal.add(LogType.ERROR);
                logTypeListFinal.add(LogType.FATAL);
                logTypeListFinal.add(LogType.WARN);
                logTypeListFinal.add(LogType.TRACE);
            } else {
                for(String type:logTypeList) {
                    logTypeListFinal.add(LogType.valueOf(type.toUpperCase()));
                }
            }
            if(trusted == null) {
                trustedFinal.add(true);
                trustedFinal.add(false);
            } else {
                for(String t:trusted) {
                    if(t.equalsIgnoreCase("false"))
                        trustedFinal.add(false);
                    else
                        trustedFinal.add(true);
                }
            }
            if(visible == null) {
                visibleFinal.add(true);
                visibleFinal.add(false);
            } else {
                for(String v:visible) {
                    if(v.equalsIgnoreCase("false"))
                        visibleFinal.add(false);
                    else
                        visibleFinal.add(true);
                }
            }
            if(createdAfter != null)
                createdAfterFinal = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(createdAfter);
            if(createdBefore != null)
                createdBeforeFinal = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(createdBefore);
            if(pageNumber != null)
                pageNumberFinal = Integer.parseInt(pageNumber);
            if(limit != null)
                limitFinal = Integer.parseInt(limit);
            if(sortBy != null)
                sortByFinal = LogEntityMapKey.valueOf(sortBy.toUpperCase());
            if(sortOrder != null)
                sortOrderFinal = SortOrder.valueOf(sortOrder.toUpperCase());
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid query param - Error: "+e.getMessage());
        }

        List<Logs> logList = logsRepositoryHandler.getLogList(logTypeListFinal, createdAfterFinal, createdBeforeFinal, tag, trustedFinal, visibleFinal, pageNumberFinal, limitFinal, sortByFinal, sortOrderFinal);
        if(logList.size() == 0) {
            responseModel.setErrMessage("No logs found for the provided filter");
            return responseModel;
        }

        for(Logs log: logList)
            responseModel.getLogList().add(entityToModel(log));

        return responseModel;
    }

    private Log entityToModel(Logs log) {
        Log logModel = Log.getLogModel(log.getLogType());
        logModel.mapDataToModel(log.getDataMap());
        return logModel;
    }

}
