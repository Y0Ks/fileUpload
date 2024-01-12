package com.sorawee.fileUpload.file.service;
import com.sorawee.fileUpload.file.dto.FileUploadResponse;
import com.sorawee.fileUpload.file.model.UploadFile;
import com.sorawee.fileUpload.file.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    private final FileUploadRepository fileUploadRepository;

    @Autowired
    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    @Override
    public FileUploadResponse getByFileName(String fileName) {
        FileUploadResponse response = new FileUploadResponse();
        UploadFile uploadedFile = fileUploadRepository.findByName(fileName);
        return response;
    }
    @Override
    public FileUploadResponse uploadFile(MultipartFile file) {
        FileUploadResponse response = new FileUploadResponse();
        return response;
    }
}

