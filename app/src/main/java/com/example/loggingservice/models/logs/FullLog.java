package com.example.loggingservice.models.logs;

import com.example.loggingservice.enums.LogEntityMapKey;
import com.example.loggingservice.enums.LogType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = true)
public class FullLog extends Log {
    private String logId;
    private String tag;
    private String message;
    private LogType logType;
    private String timestamp;
    private String createdBy;
    private String createdAt;
    private boolean trusted;
    private boolean visible;

    @Override
    public void mapDataToModel(HashMap<LogEntityMapKey, Object> dataMap) {
        this.logId = (String) dataMap.getOrDefault(LogEntityMapKey.LOG_ID, null);
        this.tag = (String) dataMap.getOrDefault(LogEntityMapKey.TAG, "DEFAULT");
        this.message = (String) dataMap.getOrDefault(LogEntityMapKey.MESSAGE, null);
        this.logType = LogType.ADMIN;
        this.timestamp = dataMap.getOrDefault(LogEntityMapKey.TIMESTAMP, "01/01/1997 00:00:00").toString();
        this.createdAt = dataMap.getOrDefault(LogEntityMapKey.CREATED_AT, "01/01/1997 00:00:00").toString();
        this.createdBy = (String) dataMap.getOrDefault(LogEntityMapKey.CREATED_BY, null);
        this.trusted = (Boolean) dataMap.getOrDefault(LogEntityMapKey.TRUSTED, false);
        this.visible = (Boolean) dataMap.getOrDefault(LogEntityMapKey.VISIBLE, false);
    }
}
