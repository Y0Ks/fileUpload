package com.sorawee.fileUpload.file.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "uploaded_file_history")
public class UploadFile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="file_id", unique=true, nullable=false, precision=19)
    private Long fileId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
