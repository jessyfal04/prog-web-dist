# gRPC Note-Taking Application

## Project Overview
This project implements a distributed note-taking application using gRPC (Google Remote Procedure Call) with Spring Boot. The application consists of three main components:
- A gRPC server that stores and manages notes
- A gRPC client that provides a web interface
- A shared interface module that defines the service contract
- A MySQL database for persistent storage
- A static resource system for handling images and other static content

## Project Structure
```
gRPCSpring/
├── myServiceInterface/          # Service contract definition
│   ├── src/main/proto/
│   │   └── myService.proto     # Protocol Buffers definition
│   └── build.gradle            # Interface module build configuration
├── myServiceServer/            # gRPC server implementation
│   ├── src/main/java/
│   │   └── com/example/myServiceServer/
│   │       ├── MyServiceImpl.java  # Server implementation
│   │       ├── model/
│   │       │   └── NoteEntity.java # JPA Entity
│   │       └── repository/
│   │           └── NoteRepository.java # JPA Repository
│   ├── src/main/resources/
│   │   └── application-k8s.yml    # Kubernetes configuration
│   └── build.gradle            # Server module build configuration
└── myServiceClient/            # Web client implementation
    ├── src/main/java/
    │   └── com/example/myServiceClient/
    │       ├── web/
    │       │   ├── NoteController.java    # MVC controller
    │       │   ├── WebService.java        # REST API endpoints
    │       │   └── Note.java              # Note model
    │       └── service/
    │           └── MyService.java         # gRPC client service
    ├── src/main/resources/
    │   └── templates/
    │       ├── create-note.html           # Create note form
    │       ├── list-notes.html            # Notes list view
    │       └── view-note.html             # Individual note view
    └── build.gradle            # Client module build configuration
```

## Technology Stack
- Java 17
- Spring Boot 3.3.3
- gRPC 1.58.0
- Protocol Buffers 3.23.4
- Thymeleaf (templating)
- Bootstrap (UI)
- Gradle (build system)
- MySQL 8.0 (database)
- JPA/Hibernate (ORM)
- Kubernetes (deployment)
- Istio (service mesh)

## Database Configuration
The application uses MySQL for persistent storage with automatic schema creation through JPA. The database configuration is managed through Kubernetes secrets and environment variables:

```yaml
# application-k8s.yml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/db?createDatabaseIfNotExist=true
    username: ${MYSQL_USER}
    password: ${MYSQL_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
```

## Database Schema
The application automatically creates the following database schema through JPA:
```sql
CREATE TABLE notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    created_at DATETIME(6),
    thumbs_up_count INT
);
```

## Functionality

### 1. Note Listing (Main Page)
- The main page (`/`) displays all notes in a card layout
- Each note card displays:
  - Username
  - Note text
  - Creation timestamp
  - Thumbs up count
- List auto-refreshes every 5 seconds
- Real-time updates without page refresh
- Data is fetched from MySQL database
- Each note card links to its individual view page

### 2. Note Creation
- Accessible at `/create`
- Users can create notes through a web form
- Each note contains:
  - Text content
  - Username
  - Creation timestamp
- Form submission is handled via AJAX
- Success/error messages are displayed without page refresh
- Notes are persisted in MySQL database

### 3. Individual Note View
- Accessible at `/notes/{id}`
- Displays detailed view of a single note
- Shows:
  - Username
  - Note text
  - Creation timestamp
  - Thumbs up count
- Provides functionality to:
  - Thumbs up the note
  - Delete the note
  - Export the note as Markdown
  - Return to the main list

### 4. Note Export
- Available on individual note view
- Exports note content as Markdown file
- Includes:
  - Note text
  - Username
  - Creation timestamp
  - Thumbs up count
- File is automatically downloaded when clicking export button

### 5. API Endpoints
- REST API:
  - POST `/api/notes`: Create a new note
  - GET `/api/notes`: List all notes
  - GET `/api/notes/{id}`: Get a single note by ID
  - GET `/api/notes/{id}/markdown`: Export a note as Markdown
  - POST `/api/notes/{noteId}/thumbs-up`: Increment thumbs up count for a note
  - DELETE `/api/notes/{noteId}`: Delete a specific note
- gRPC Service:
  - `CreateNote`: Server-side note creation (returns note ID)
  - `ListNotes`: Server-side note retrieval
  - `GetNote`: Server-side single note retrieval
  - `UpdateNoteThumbsUp`: Server-side thumbs up update
  - `DeleteNote`: Server-side note deletion

## Implementation Details

### Protocol Buffers Definition (`myService.proto`)
```protobuf
service MyService {
  rpc CreateNote (CreateNoteRequest) returns (CreateNoteResponse) {}
  rpc ListNotes (ListNotesRequest) returns (ListNotesResponse) {}
  rpc GetNote (GetNoteRequest) returns (GetNoteResponse) {}
  rpc UpdateNoteThumbsUp (UpdateNoteThumbsUpRequest) returns (UpdateNoteThumbsUpResponse) {}
  rpc DeleteNote (DeleteNoteRequest) returns (DeleteNoteResponse) {}
}

message CreateNoteRequest {
  string text = 1;
  string username = 2;
}

message CreateNoteResponse {
  bool success = 1;
  string message = 2;
  string noteId = 3;
}

message ListNotesRequest {}

message ListNotesResponse {
  repeated Note notes = 1;
}

message GetNoteRequest {
  string note_id = 1;
}

message GetNoteResponse {
  bool success = 1;
  string message = 2;
  Note note = 3;
}

message Note {
  string id = 1;
  string text = 2;
  string username = 3;
  string created_at = 4;
  int32 thumbs_up_count = 5;
}

message UpdateNoteThumbsUpRequest {
  string noteId = 1;
}

message UpdateNoteThumbsUpResponse {
  bool success = 1;
  string message = 2;
  int32 thumbs_up_count = 3;
}

message DeleteNoteRequest {
  string noteId = 1;
}

message DeleteNoteResponse {
  bool success = 1;
  string message = 2;
}
```

### Server Implementation (`MyServiceImpl.java`)
- MySQL database storage using JPA
- Handles note creation and listing
- Uses `@GrpcService` annotation
- Maintains note list with timestamps
- Automatic table creation and updates

### Client Implementation
1. **Web Interface**:
   - Thymeleaf templates for views
   - Bootstrap for styling
   - AJAX for form submission
   - Real-time updates
   - Individual note view with export functionality

2. **REST API**:
   - JSON responses
   - Proper error handling
   - Clean separation of concerns

3. **gRPC Client**:
   - `@GrpcClient` annotation
   - Service stub injection
   - Protocol Buffers message handling