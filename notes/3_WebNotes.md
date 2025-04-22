# gRPC Note-Taking Application

## Project Overview
This project implements a distributed note-taking application using gRPC (Google Remote Procedure Call) with Spring Boot. The application consists of three main components:
- A gRPC server that stores and manages notes
- A gRPC client that provides a web interface
- A shared interface module that defines the service contract

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
│   │       └── MyServiceImpl.java  # Server implementation
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
    │       └── list-notes.html            # Notes list view
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

## Functionality

### 1. Note Creation
- Users can create notes through a web form
- Each note contains:
  - Text content
  - Username
  - Creation timestamp
- Form submission is handled via AJAX
- Success/error messages are displayed without page refresh

### 2. Note Listing
- Users can view all notes in a card layout
- Each note card displays:
  - Username
  - Note text
  - Creation timestamp
- List auto-refreshes every 5 seconds
- Real-time updates without page refresh

### 3. API Endpoints
- REST API:
  - POST `/api/notes`: Create a new note
  - GET `/api/notes`: List all notes
- gRPC Service:
  - `CreateNote`: Server-side note creation
  - `ListNotes`: Server-side note retrieval

## Implementation Details

### Protocol Buffers Definition (`myService.proto`)
```protobuf
service MyService {
  rpc CreateNote (CreateNoteRequest) returns (CreateNoteResponse) {}
  rpc ListNotes (ListNotesRequest) returns (ListNotesResponse) {}
}

message CreateNoteRequest {
  string text = 1;
  string username = 2;
}

message CreateNoteResponse {
  bool success = 1;
  string message = 2;
}

message ListNotesRequest {}

message ListNotesResponse {
  repeated Note notes = 1;
}

message Note {
  string text = 1;
  string username = 2;
  string created_at = 3;
}
```

### Server Implementation (`MyServiceImpl.java`)
- In-memory note storage
- Handles note creation and listing
- Uses `@GrpcService` annotation
- Maintains note list with timestamps

### Client Implementation
1. **Web Interface**:
   - Thymeleaf templates for views
   - Bootstrap for styling
   - AJAX for form submission
   - Real-time updates

2. **REST API**:
   - JSON responses
   - Proper error handling
   - Clean separation of concerns

3. **gRPC Client**:
   - `@GrpcClient` annotation
   - Service stub injection
   - Protocol Buffers message handling

## Build and Run

1. Build the project:
```bash
./gradlew clean build
```

2. Start the server:
```bash
cd myServiceServer
java -jar build/libs/myServiceServer-0.0.1-SNAPSHOT.jar
```

3. Start the client:
```bash
cd myServiceClient
java -jar build/libs/myServiceClient-0.0.1-SNAPSHOT.jar
```

4. Access the application:
   - Create notes: http://localhost:8080/
   - View notes: http://localhost:8080/notes

## Dependencies
- Spring Boot 3.3.3
- Spring Dependency Management 1.1.6
- gRPC Spring Boot Starter 2.15.0.RELEASE
- Thymeleaf
- Bootstrap 5.1.3
- Protocol Buffers 3.23.4
- gRPC 1.58.0
- Protobuf Gradle Plugin 0.8.18

## Configuration
- Server port: 8080
- gRPC server port: 9090
- Thymeleaf template configuration
- Java 17 toolchain
- Gradle build system with modern task configuration

## Future Improvements
1. Persistent storage (database integration)
2. User authentication
3. Note editing and deletion
4. Real-time updates using WebSocket
5. Enhanced error handling
6. Input validation
7. Rate limiting
8. Caching layer