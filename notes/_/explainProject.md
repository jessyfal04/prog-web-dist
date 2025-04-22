Based on the project structure and documentation, I can explain this project to you:
This is a gRPC (Google Remote Procedure Call) project built with Spring Boot, demonstrating a client-server architecture. The project is divided into three main components:

myServiceInterface:
This module defines the service contract using Protocol Buffers (proto files)
It contains the service definitions that both client and server will use
This is the shared interface between client and server

myServiceServer:
This is the server implementation of the gRPC service
It implements the service defined in the interface module
Uses the @GrpcService annotation to mark the service implementation
Runs on port 9090 by default

myServiceClient:
This is a Spring Boot application that acts as a client to the gRPC service
It exposes a REST endpoint (http://localhost:8080/world)
Uses @GrpcClient annotation to connect to the gRPC server
Communicates with the server running on localhost:9090

The project uses:
Java 17
Gradle as the build system
Spring Boot for both client and server
Protocol Buffers for service definition
gRPC fort:
First buil remote procedure calls
To run the projecd the entire project using ./gradlew build in the root directory
Start the server by running the server JAR file
Start the client by running the client JAR file
Access the service through the client's REST endpoint at http://localhost:8080/world
This is a good example of how to implement a microservice architecture using gRPC with Spring Boot, where the client and server communicate efficiently using Protocol Buffers instead of traditional REST APIs.