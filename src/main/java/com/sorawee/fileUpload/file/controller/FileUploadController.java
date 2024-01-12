package com.sorawee.fileUpload.file.controller;

import com.sorawee.fileUpload.FileUploadApplication;
import com.sorawee.fileUpload.file.dto.ErrorResponse;
import com.sorawee.fileUpload.file.dto.FileUploadResponse;
import com.sorawee.fileUpload.file.service.FileUploadService;
import com.sorawee.fileUpload.notification.service.EmailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/file/")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private final FileUploadService fileUploadService;


    public FileUploadController(FileUploadService fileUploadService, EmailService emailService) {
        this.fileUploadService = fileUploadService;
    }


    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            if (file == null || file.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("File is empty or not provided");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(errorResponse);
            } else {

                FileUploadResponse result = fileUploadService.uploadFile(file);

                if (result != null) {
                    if (result.getErrorMessage().isEmpty()) {
                        return ResponseEntity.ok(result);
                    } else {
                        return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(result);
                    }
                } else {
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("An internal error occurred. Please try again later. : " + "UNKNOWN ERROR");
                }
            }

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("An internal error occurred. Please try again later. : " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);

        }
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFile(@RequestParam(name = "fileName", required = false) String fileName) {
        if (fileName == null) {
            ErrorResponse errorResponse = new ErrorResponse("Parameter is empty");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        } else if (!fileName.isEmpty()) {
            FileUploadResponse result = fileUploadService.getByFileName(fileName);
            String resultFilePath = "";
            if (result != null) {
                resultFilePath = result.getFilePath();
            }
            return ResponseEntity.ok(Map.of("filePath", resultFilePath));
        } else {

            ErrorResponse errorResponse = new ErrorResponse("Invalid format parameter");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
    }

    @PutMapping(value = "/put", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> putFile() {

        ErrorResponse errorResponse = new ErrorResponse("PUT method is not allowed.");
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorResponse);
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> deleteFile() {

        ErrorResponse errorResponse = new ErrorResponse("DELETE method is not allowed.");
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorResponse);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ErrorResponse> handleMultipartException(MultipartException e) {
        ErrorResponse errorResponse = new ErrorResponse("File is empty or not provided");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
