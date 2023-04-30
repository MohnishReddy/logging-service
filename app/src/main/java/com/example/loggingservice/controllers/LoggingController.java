package com.example.loggingservice.controllers;

import com.example.loggingservice.enums.LogType;
import com.example.loggingservice.models.requests.AddLogRequestModel;
import com.example.loggingservice.models.responses.GetLogsResponseModel;
import com.example.loggingservice.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1")
@ResponseBody
public class LoggingController {

    @Autowired
    private LoggingService loggingService;

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<String> addLog(@RequestBody AddLogRequestModel logRequest) {
        String tag = logRequest.getTag();
        String message = logRequest.getMessage();
        String logType = logRequest.getLogType();
        String timestampStr = logRequest.getTimestamp();
        String createdBy = logRequest.getCreatedBy();

        Date timestamp;
        try {
            timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(timestampStr);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            String logId = loggingService.addLog(tag, message, LogType.valueOf(logType), timestamp, createdBy);
            return new ResponseEntity<>(logId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/get/{logId}", produces = "application/json")
    public ResponseEntity<?> getLog(@PathVariable String logId) {
        try {
            GetLogsResponseModel response = loggingService.getLogById(logId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
