package com.example.loggingservice.services;

import com.example.loggingservice.entities.Logs;
import com.example.loggingservice.repositories.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        if(logOpt.isPresent()) {
            return logOpt.get();
        } else {
            throw new NoSuchElementException("No such log exists");
        }
    }

}
