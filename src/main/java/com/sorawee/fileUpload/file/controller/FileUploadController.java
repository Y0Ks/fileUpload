package com.sorawee.fileUpload.file.controller;

import com.sorawee.fileUpload.file.dto.ErrorResponse;
import com.sorawee.fileUpload.file.dto.FileUploadResponse;
import com.sorawee.fileUpload.file.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(path = "/file")
    public ResponseEntity<?> postFile(@RequestParam("file") MultipartFile file) {

        try {
            if (file == null || file.isEmpty()) {
                ErrorResponse errorResponse = new ErrorResponse("File is empty or not provided");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(errorResponse);
            } else {

                FileUploadResponse result = fileUploadService.uploadFile(file);

                return ResponseEntity.ok(result);
            }

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("An internal error occurred. Please try again later. : " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);

        }
    }

    @PutMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> putFile() {

        ErrorResponse errorResponse = new ErrorResponse("PUT method is not allowed.");
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorResponse);
    }

    @DeleteMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> deleteFile() {

        ErrorResponse errorResponse = new ErrorResponse("DELETE method is not allowed.");
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorResponse);
    }
}
