package com.example.loggingservice.models.logs;

import com.example.loggingservice.enums.LogEntityMapKey;
import com.example.loggingservice.enums.LogType;

import java.util.HashMap;

public abstract class Log {

    public abstract void mapDataToModel(HashMap<LogEntityMapKey, Object> dataMap);

    public static Log getLogModel(LogType logType) {
        switch (logType) {
            case INFO -> {
                return new InfoLog();
            }
            default -> {
                return new FullLog();
            }
        }
    }
}
