package com.example.loggingservice.controllers;

import com.example.loggingservice.models.requests.AddLogRequestModel;
import com.example.loggingservice.models.requests.UpdateLogRequestModel;
import com.example.loggingservice.models.responses.AddLogResponseModel;
import com.example.loggingservice.models.responses.GetLogsResponseModel;
import com.example.loggingservice.models.responses.UpdateLogResponseModel;
import com.example.loggingservice.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1")
@ResponseBody
public class LoggingController {

    @Autowired
    private LoggingService loggingService;

    @CrossOrigin
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<AddLogResponseModel> addLog(@RequestBody AddLogRequestModel logRequest) {
        AddLogResponseModel response = new AddLogResponseModel();
        try {
            response = loggingService.addLog(logRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (HttpClientErrorException e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatusCode());
        } catch (Exception e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/log/{logId}", produces = "application/json")
    public ResponseEntity<GetLogsResponseModel> getLog(@PathVariable String logId) {
        GetLogsResponseModel response = new GetLogsResponseModel();
        try {
            response = loggingService.getLogById(logId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping(path = "/log/{logId}/update", produces = "application/json")
    public ResponseEntity<UpdateLogResponseModel> updateLog(@PathVariable String logId, @RequestBody UpdateLogRequestModel updateReq) {
        UpdateLogResponseModel response = new UpdateLogResponseModel();
        try {
            response = loggingService.updateLog(logId, updateReq);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping(path = "/log/list", produces = "application/json")
    public ResponseEntity<GetLogsResponseModel> getFilteredListOfLogs(
            @RequestParam(required = false) String[] logTypeList,
            @RequestParam(required = false) String createdAfter,
            @RequestParam(required = false) String createdBefore,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String[] trusted,
            @RequestParam(required = false) String[] visible,
            @RequestParam(required = false) String pageNumber,
            @RequestParam(required = false) String limit,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder
        )
    {
        GetLogsResponseModel response = new GetLogsResponseModel();
        try {
            response = loggingService.getFilteredListOfLogs(logTypeList, createdAfter, createdBefore, tag, trusted, visible, pageNumber, limit, sortBy, sortOrder);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getStatusCode());
        } catch (Exception e) {
            response.setErrMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
