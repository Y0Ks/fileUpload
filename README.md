
CREATE TABLE UPLOADED_FILE_HISTORY (
    FILE_ID BIGINT IDENTITY(1,1) PRIMARY KEY,
    FILE_NAME NVARCHAR(255) NOT NULL,
    FILE_SIZE BIGINT,
    CREATED_DATE DATETIME2,
    FILE_PATH NVARCHAR(255)
);
