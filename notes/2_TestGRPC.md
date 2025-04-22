# Move into folder
cd gRPCSpring

# Build everything
./gradlew clean build

# ── Server ─────────────────────────────────────
cd myServiceServer
java -jar build/libs/myServiceServer-0.0.1-SNAPSHOT.jar
## (defaults to listening on localhost:9090 due to application.properties)

# ── Client ─────────────────────────────────────
## In a separate shell:
cd myServiceClient
java -jar build/libs/myServiceClient-0.0.1-SNAPSHOT.jar

## Test via REST → gRPC bridge:
curl http://localhost:8080/world