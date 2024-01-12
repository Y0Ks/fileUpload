package com.sorawee.fileUpload.file.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "UPLOADED_FILE_HISTORY")
public class UploadFile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="FILE_ID", unique=true, nullable=false, precision=19)
    private Long fileId;

    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "FILE_PATH")
    private String filePath;


}
