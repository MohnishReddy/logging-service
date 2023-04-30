package com.example.loggingservice.entities;

import com.example.loggingservice.enums.LogEntityMapKey;
import com.example.loggingservice.enums.LogType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Data
@Document(collection = "logs")
public class Logs {
    @MongoId
    private String logId;
    private String tag;
    private String message;
    private LogType logType;
    private Date timestamp;
    private String createdBy;
    private Date createdAt;
    private boolean trusted;
    private boolean visible;

    public Logs(String tag, String message, LogType logType, Date timestamp, String createdBy) {
        this.logId = getUniqueId();
        this.tag = tag;
        this.message = message;
        this.logType = logType;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
        this.createdAt = new Date();
        this.trusted = isTrusted();
        this.visible = true;
    }

    public Logs() {}

    public HashMap<LogEntityMapKey, Object> getDataMap() {
        HashMap<LogEntityMapKey, Object> dataMap = new HashMap<>();

        dataMap.put(LogEntityMapKey.LOG_ID, this.logId);
        dataMap.put(LogEntityMapKey.TAG, this.tag);
        dataMap.put(LogEntityMapKey.MESSAGE, this.message);
        dataMap.put(LogEntityMapKey.LOG_TYPE, this.logType);
        dataMap.put(LogEntityMapKey.TIMESTAMP, this.timestamp);
        dataMap.put(LogEntityMapKey.CREATED_BY, this.createdBy);
        dataMap.put(LogEntityMapKey.CREATED_AT, this.createdAt);
        dataMap.put(LogEntityMapKey.TRUSTED, this.trusted);
        dataMap.put(LogEntityMapKey.VISIBLE, this.visible);

        return dataMap;
    }

    private String getUniqueId() {
        return UUID.randomUUID().toString();
    }

    private boolean isTrusted() {
        return true; // TODO implement a way to identify a trusted request
    }
}
