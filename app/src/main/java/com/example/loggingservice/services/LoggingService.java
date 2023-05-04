package com.example.loggingservice.services;

import com.example.loggingservice.models.requests.AddLogRequestModel;
import com.example.loggingservice.models.requests.UpdateLogRequestModel;
import com.example.loggingservice.models.responses.AddLogResponseModel;
import com.example.loggingservice.models.responses.GetLogsResponseModel;
import com.example.loggingservice.models.responses.UpdateLogResponseModel;

public interface LoggingService {
    AddLogResponseModel addLog(AddLogRequestModel logRequest) throws Exception;
    GetLogsResponseModel getLogById(String logId);
    UpdateLogResponseModel updateLog(String logId, UpdateLogRequestModel updateReq);
    GetLogsResponseModel getFilteredListOfLogs(String[] logTypeList, String createdAfter, String createdBefore, String tag, String[] trusted, String[] visible, String pageNumber, String limit, String sortBy, String sortOrder) throws Exception;
}
