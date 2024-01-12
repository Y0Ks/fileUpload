package com.sorawee.fileUpload.file.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorResponse {
    private String errorMessage;
    private LocalDateTime errorTime;

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorTime = LocalDateTime.now();
    }
}