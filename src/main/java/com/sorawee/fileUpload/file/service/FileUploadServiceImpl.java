package com.sorawee.fileUpload.file.service;

import com.sorawee.fileUpload.file.dto.FileUploadResponse;
import com.sorawee.fileUpload.file.helper.FileUtil;
import com.sorawee.fileUpload.file.model.UploadFile;
import com.sorawee.fileUpload.file.repository.FileUploadRepository;
import com.sorawee.fileUpload.notification.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);
    private final FileUploadRepository fileUploadRepository;
    private final EmailService emailService;

    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository, EmailService emailService) {
        this.fileUploadRepository = fileUploadRepository;
        this.emailService = emailService;
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


        try {
            UploadFile transferredFile = transferFile(file);
            if (transferredFile == null) {
                response.setErrorMessage("Transfer file failed.");
                return response;
            }

            transferredFile.setCreatedDate(LocalDateTime.now());


            response.setFilePath(transferredFile.getFilePath());
            response.setFileName(transferredFile.getFileName());
            response.setCreatedDate(transferredFile.getCreatedDate());
            response.setFileSize(transferredFile.getFileSize().toString());


            UploadFile savedFile = fileUploadRepository.save(transferredFile);
            logger.info("Saved file with ID : " + savedFile.getFileId());

            boolean isEmailSend = sentEmail(savedFile);
            if (!isEmailSend) {
                response.setErrorMessage("Transfer file success BUT email failed to send.");
                return response;
            }
        } catch (Exception e) {
            logger.error("Error in transferFile : " + e);
            return response;
        }
        return response;
    }

    private boolean sentEmail(UploadFile obj) {
        String recipient = "yok.sorawee@gmail.com";
        String subject = "test: upload file report";
        String name = "Yok";
        String content = "<p style=\"font-size:11.0pt;font-family:'Tahoma',sans-serif;\" >" + "Dear Khun "
                + name + "<br>" + "<br>" + "Your file has been uploaded :  " + String.valueOf(obj.getFilePath()).trim()
                + "<br>" + "Yours sincerely,<br>"
                + "File Upload Service <br>" + "</p>";
        return this.emailService.sentEmail(recipient, subject, content);

    }

    private UploadFile transferFile(MultipartFile file) {


        try {
            return FileUtil.transferFile(file, "D:/",
                    "uploadedFile/");


        } catch (Exception e) {
            logger.error("Error in transferFile : " + e);
            return null;
        }
    }
}

