package com.sorawee.fileUpload.file.repository;

import com.sorawee.fileUpload.file.model.UploadFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface FileUploadRepository extends CrudRepository<UploadFile, Long> {

    @Query(value = "SELECT * FROM [UPLOADED_FILE_HISTORY] "
            + " WHERE FILE_NAME like :fileName% ", nativeQuery = true)
    UploadFile findByName(@Param("fileName") String fileName);
}
