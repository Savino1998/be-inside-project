package com.reply.insideproject.error;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ApiError {

    private String status;
    private String timestamp;
    private String message;

    public ApiError(String status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
