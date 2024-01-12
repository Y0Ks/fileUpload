CREATE TABLE uploaded_file_history (
file_id BIGINT IDENTITY(1,1) PRIMARY KEY,
file_name NVARCHAR(255) NOT NULL,
file_size BIGINT,
created_date DATETIME2,
file_path NVARCHAR(255)
);
