
# We need to build the project first
./gradlew clean build

# We create two DockerFile for the client and the myServiceInterface
## myServiceServer
```
# myServiceServer/Dockerfile
FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 9090
ADD build/libs/myServiceServer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```

## myServiceClient
```
# myServiceClient/Dockerfile
FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8080
ADD build/libs/myServiceClient-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```

# We login
docker login
We need to create a Personnal Access Token on the website first

cd myServiceServer
docker build -t jessyfal04/webnotes-server:latest .
docker push jessyfal04/webnotes-server:latest

# Build client
cd myServiceClient
docker build -t jessyfal04/webnotes-client:latest .
docker push jessyfal04/webnotes-client:latest