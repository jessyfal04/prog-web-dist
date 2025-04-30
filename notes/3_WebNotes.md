# gRPC Note-Taking Application

## Project Overview
This project implements a distributed note-taking application using gRPC (Google Remote Procedure Call) with Spring Boot. The application consists of three main components:
- A gRPC server that stores and manages notes
- A gRPC client that provides a web interface
- A shared interface module that defines the service contract
- A MySQL database for persistent storage
- A static resource system for handling images, stylesheets, and other static content

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
- Sorting options:
  - Most Recent (default): Orders notes by creation date, newest first
  - Most Popular: Orders notes by thumbs up count, highest first

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
    - Query parameter `sortByPopularity`: boolean (default: false)
      - false: Sort by most recent
      - true: Sort by most popular
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
The service defines the following RPC methods and messages:

#### RPC Methods
1. `CreateNote`: Create a new note
   - Request: text, username
   - Response: success, message, noteId

2. `ListNotes`: Get all notes with optional sorting
   - Request: sortBy (RECENT/POPULAR)
   - Response: list of notes

3. `GetNote`: Get a single note by ID
   - Request: note_id
   - Response: success, message, note

4. `UpdateNoteThumbsUp`: Increment thumbs up count
   - Request: noteId
   - Response: success, message, thumbs_up_count

5. `DeleteNote`: Delete a note
   - Request: noteId
   - Response: success, message

#### Message Types
1. `Note`: Represents a note
   - id: Unique identifier
   - text: Note content
   - username: Author
   - created_at: Creation timestamp
   - thumbs_up_count: Number of thumbs up

2. `ListNotesRequest`: Request for listing notes
   - sortBy: Sorting option (RECENT/POPULAR)

3. `ListNotesResponse`: Response containing notes
   - notes: List of Note objects

4. `CreateNoteRequest`: Request for creating a note
   - text: Note content
   - username: Author

5. `CreateNoteResponse`: Response after note creation
   - success: Operation status
   - message: Status message
   - noteId: Created note's ID

6. `GetNoteRequest`: Request for a single note
   - note_id: Note identifier

7. `GetNoteResponse`: Response with a single note
   - success: Operation status
   - message: Status message
   - note: Note object

8. `UpdateNoteThumbsUpRequest`: Request to thumbs up
   - noteId: Note identifier

9. `UpdateNoteThumbsUpResponse`: Response after thumbs up
   - success: Operation status
   - message: Status message
   - thumbs_up_count: Updated count

10. `DeleteNoteRequest`: Request to delete a note
    - noteId: Note identifier

11. `DeleteNoteResponse`: Response after deletion
    - success: Operation status
    - message: Status message

### Server Implementation (`MyServiceImpl.java`)
- MySQL database storage using JPA
- Handles note creation and listing
- Uses `@GrpcService` annotation
- Maintains note list with timestamps
- Automatic table creation and updates
- Supports sorting by:
  - Most recent (default)
  - Most popular (by thumbs up count)

### Client Implementation
1. **Web Interface**:
   - Thymeleaf templates for views
   - Bootstrap for modern, responsive UI
   - AJAX for form submission
   - Real-time updates
   - Individual note view with export functionality
   - Sorting controls for note list
   - Hover effects and animations
   - Clean, modern card layout

2. **REST API**:
   - JSON responses
   - Proper error handling
   - Clean separation of concerns
   - Sorting support via query parameters

3. **gRPC Client**:
   - `@GrpcClient` annotation
   - Service stub injection
   - Protocol Buffers message handling
   - Sorting support implementation

### Static Resources
The application uses a static resource system for serving:
- Images (favicon.png)
- Stylesheets (styles.css)
- Other static content

The static resources are served from the `/static` directory and are accessible via the `/static/**` URL pattern. The resources are mounted in Kubernetes using a PersistentVolume and PersistentVolumeClaim.