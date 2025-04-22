package com.example.myServiceServer;

import com.example.lib.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {
    
    private final List<Note> notes = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void createNote(CreateNoteRequest request, StreamObserver<CreateNoteResponse> responseObserver) {
        String createdAt = LocalDateTime.now().format(formatter);
        Note note = Note.newBuilder()
                .setText(request.getText())
                .setUsername(request.getUsername())
                .setCreatedAt(createdAt)
                .build();
        
        notes.add(note);
        
        CreateNoteResponse response = CreateNoteResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Note created successfully")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listNotes(ListNotesRequest request, StreamObserver<ListNotesResponse> responseObserver) {
        ListNotesResponse response = ListNotesResponse.newBuilder()
                .addAllNotes(notes)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
