package com.example.loggingservice.services;

import com.example.loggingservice.entities.Logs;
import com.example.loggingservice.enums.LogEntityMapKey;
import com.example.loggingservice.enums.LogType;
import com.example.loggingservice.enums.SortOrder;
import com.example.loggingservice.repositories.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogsRepositoryHandlerImpl implements LogsRepositoryHandler {

    @Autowired
    private LogsRepository repo;

    public String saveLog(Logs log) {
        Logs savedData = repo.save(log);
        return savedData.getLogId();
    }

    public Logs getLogById(String logId) {
        Optional<Logs> logOpt = repo.findById(logId);
        return logOpt.orElse(null);
    }

    public List<Logs> getLogList(List<LogType> logTypeList, Date createdAfter, Date createdBefore, String tag, List<Boolean> trusted, List<Boolean> visible, int pageNumber, int limit, LogEntityMapKey sortBy, SortOrder sortOrder) {
        int skip = pageNumber*limit;
        if(tag == null)
            return repo.find(logTypeList, createdAfter, createdBefore, trusted, visible, skip, limit, Logs.getDbFieldName(sortBy), ((sortOrder == SortOrder.ASC)?1:-1));
        else
            return repo.find(logTypeList, createdAfter, createdBefore, tag, trusted, visible, skip, limit, Logs.getDbFieldName(sortBy), ((sortOrder == SortOrder.ASC)?1:-1));
    }

}
