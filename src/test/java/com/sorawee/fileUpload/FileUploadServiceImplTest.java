package com.sorawee.fileUpload;

import com.sorawee.fileUpload.file.dto.FileUploadResponse;
import com.sorawee.fileUpload.file.repository.FileUploadRepository;
import com.sorawee.fileUpload.file.service.FileUploadServiceImpl;
import com.sorawee.fileUpload.notification.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadServiceImplTest {

    @InjectMocks
    private FileUploadServiceImpl fileUploadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void uploadFile_withValidFile_returnsSuccessfulUploadResponse() throws IOException {
        MultipartFile validFile = new MockMultipartFile("file", "test.txt", "text/plain", "Sample content".getBytes());

        FileUploadResponse response = fileUploadService.uploadFile(validFile);

        assertNotNull(response, "Response should not be null");
        assertNull(response.getErrorMessage(), "Error message should be null for a successful upload");
    }

    @Test
    void uploadFile_withEmptyFile_returnsErrorResponse_FileEmpty() {
        MultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);

        FileUploadResponse response = fileUploadService.uploadFile(emptyFile);

        assertNotNull(response, "Response should not be null");
        assertNotNull(response.getErrorMessage(), "Error message should not be null for an empty file");
    }

}
