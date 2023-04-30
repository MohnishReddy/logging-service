package com.example.loggingservice.models.logs;

import com.example.loggingservice.enums.LogEntityMapKey;
import com.example.loggingservice.enums.LogType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@Data
@EqualsAndHashCode(callSuper = true)
public class InfoLog extends Log {
    private String logId;
    private String message;
    private LogType logType;
    private String timestamp;
    private boolean trusted;
    private boolean visible;

    @Override
    public void mapDataToModel(HashMap<LogEntityMapKey, Object> dataMap) {
        this.logId = (String) dataMap.getOrDefault(LogEntityMapKey.LOG_ID, null);
        this.message = (String) dataMap.getOrDefault(LogEntityMapKey.MESSAGE, "");
        this.logType = LogType.INFO;
        this.timestamp = dataMap.getOrDefault(LogEntityMapKey.TIMESTAMP, "01/01/1997 00:00").toString();
        this.trusted = (Boolean) dataMap.getOrDefault(LogEntityMapKey.TRUSTED, false);
        this.visible = (Boolean) dataMap.getOrDefault(LogEntityMapKey.VISIBLE, false);
    }
}
