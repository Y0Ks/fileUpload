package com.sorawee.fileUpload.file.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileUploadResponse {

    private String fileName;
    private String filePath;
    private String fileSize;
    private LocalDateTime createdDate;

    private String errorMessage;

}
