package com.sorawee.fileUpload.file.service;
import com.sorawee.fileUpload.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    FileUploadResponse getByFileName(String fileName);
    FileUploadResponse uploadFile(MultipartFile file);
}
