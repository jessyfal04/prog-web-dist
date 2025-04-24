package com.example.myServiceServer;

import com.example.lib.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.Optional;

@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {
    
    private final List<Note> notes = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void createNote(CreateNoteRequest request, StreamObserver<CreateNoteResponse> responseObserver) {
        String createdAt = LocalDateTime.now().format(formatter);
        String noteId = UUID.randomUUID().toString();
        
        Note note = Note.newBuilder()
                .setId(noteId)
                .setText(request.getText())
                .setUsername(request.getUsername())
                .setCreatedAt(createdAt)
                .setThumbsUpCount(0)
                .build();
        
        notes.add(note);
        
        CreateNoteResponse response = CreateNoteResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Note created successfully")
                .setNoteId(noteId)
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

    @Override
    public void updateNoteThumbsUp(UpdateNoteThumbsUpRequest request, StreamObserver<UpdateNoteThumbsUpResponse> responseObserver) {
        Optional<Note> noteOpt = notes.stream()
                .filter(n -> n.getId().equals(request.getNoteId()))
                .findFirst();

        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            int newCount = note.getThumbsUpCount() + 1;
            
            Note updatedNote = Note.newBuilder(note)
                    .setThumbsUpCount(newCount)
                    .build();
            
            notes.set(notes.indexOf(note), updatedNote);
            
            UpdateNoteThumbsUpResponse response = UpdateNoteThumbsUpResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Thumbs up updated successfully")
                    .setThumbsUpCount(newCount)
                    .build();
            
            responseObserver.onNext(response);
        } else {
            UpdateNoteThumbsUpResponse response = UpdateNoteThumbsUpResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Note not found")
                    .setThumbsUpCount(0)
                    .build();
            
            responseObserver.onNext(response);
        }
        
        responseObserver.onCompleted();
    }

    @Override
    public void deleteNote(DeleteNoteRequest request, StreamObserver<DeleteNoteResponse> responseObserver) {
        boolean removed = notes.removeIf(note -> note.getId().equals(request.getNoteId()));
        
        DeleteNoteResponse response = DeleteNoteResponse.newBuilder()
                .setSuccess(removed)
                .setMessage(removed ? "Note deleted successfully" : "Note not found")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
