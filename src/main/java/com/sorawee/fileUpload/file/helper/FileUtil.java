package com.sorawee.fileUpload.file.helper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.sorawee.fileUpload.FileUploadApplication;
import com.sorawee.fileUpload.file.model.UploadFile;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadApplication.class);

    @Transactional
    public static UploadFile transferFile(MultipartFile file, String defaultPath, String imgFolder) {

        UploadFile result = new UploadFile();
        if (!file.isEmpty()) {
            try {
                String realPathtoUploads =  defaultPath + imgFolder;

                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }

                String originalFileName = file.getOriginalFilename();

                String fileType = "jpg";
                if (!originalFileName.equalsIgnoreCase("")) {

                    int i = originalFileName.lastIndexOf('.');
                    if (i > 0) {
                        fileType = originalFileName.substring(i + 1);
                    }
                }

                String datetime = LocalDateTime.now().toString();
                datetime = datetime.replaceAll("\\ {1}", "").replaceAll("\\:{1}", "").replaceAll("\\.{1}", "").replaceAll("\\-{1}", "").replaceAll("\\_{1}", "");

                String newFileName = datetime+"_"+ RandomStringUtils.randomAlphabetic(3)+'.'+fileType;
                String filePath = realPathtoUploads + newFileName ;

                logger.info("original file name :"+ originalFileName);
                logger.info("new file name :"+ newFileName);

                File dest = new File(filePath);
                file.transferTo(dest);
                result.setFileSize(file.getSize());
                result.setFileName(newFileName);
                result.setFilePath(filePath);
            } catch (Exception e) {
                return null;
            }
        }else{
            return null;
        }
        return result;

    }

}
