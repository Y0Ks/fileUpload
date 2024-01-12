# File Upload Service

## Overview
File Upload Service is a demo project.

## Features
1. **JWT Authentication**: Secure endpoints using JWT tokens.
2. **Scalability**: Organized in a modular fashion for easy scaling.
3. **Testability**: Unit tests implemented with Mockito for 2 examples.
4. **RESTful API**: Adheres to RESTful standards for API design.
5. **File Storage**: Files are stored in a specified directory (default: `D:\uploadedFile`).
6. **Database Integration**: Integration with MSSQL for storing file metadata.
7. **Email Notification**: Sends email notifications on successful file uploads (SMTP configuration required).

## Getting Started

### Prerequisites
- Java JDK 8 or later
- Maven
- MSSQL Database
- SMTP Server (for email notifications)

### Database Setup
Run the following SQL script to create the necessary table in your MSSQL database:

```sql
CREATE TABLE uploaded_file_history (
    file_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    file_name NVARCHAR(255) NOT NULL,
    file_size BIGINT,
    created_date DATETIME2,
    file_path NVARCHAR(255)
    );
```


### Setting Up the Project
1. Clone the repository: git clone https://github.com/Y0Ks/fileUpload.git
2. Navigate to the project directory:
3. Configure the `application.properties` file with your database and SMTP server details.

### Running the Application
Execute the following command to start the application:
mvn spring-boot:run

### Using the Service
1. **Authenticate**: Obtain a JWT token using your credentials.
2. **Upload File**: Make a POST request to `/upload` with the JWT token and the file data.
3. **Email Notification**: On successful upload, an email notification is sent to the specified recipient.

## API Documentation
Access the Swagger UI for API documentation and testing at:
http://localhost:8080/swagger-ui.html

## Running Tests
Execute the unit tests with:
mvn test

## Remark
Any user and password can be used to generate a token in this demo.


Sorawee P.
