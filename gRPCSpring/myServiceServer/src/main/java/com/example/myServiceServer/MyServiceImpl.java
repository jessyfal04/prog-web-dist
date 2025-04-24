package com.example.myServiceServer;

import com.example.lib.*;
import com.example.myServiceServer.model.NoteEntity;
import com.example.myServiceServer.repository.NoteRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void createNote(CreateNoteRequest request, StreamObserver<CreateNoteResponse> responseObserver) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setText(request.getText());
        noteEntity.setUsername(request.getUsername());
        noteEntity.setCreatedAt(LocalDateTime.now());
        noteEntity.setThumbsUpCount(0);
        
        noteEntity = noteRepository.save(noteEntity);
        
        CreateNoteResponse response = CreateNoteResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Note created successfully")
                .setNoteId(noteEntity.getId().toString())
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listNotes(ListNotesRequest request, StreamObserver<ListNotesResponse> responseObserver) {
        List<NoteEntity> noteEntities = noteRepository.findAll();
        
        List<Note> notes = noteEntities.stream()
                .map(entity -> Note.newBuilder()
                        .setId(entity.getId().toString())
                        .setText(entity.getText())
                        .setUsername(entity.getUsername())
                        .setCreatedAt(entity.getCreatedAt().format(formatter))
                        .setThumbsUpCount(entity.getThumbsUpCount())
                        .build())
                .collect(Collectors.toList());
        
        ListNotesResponse response = ListNotesResponse.newBuilder()
                .addAllNotes(notes)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateNoteThumbsUp(UpdateNoteThumbsUpRequest request, StreamObserver<UpdateNoteThumbsUpResponse> responseObserver) {
        Long noteId = Long.parseLong(request.getNoteId());
        NoteEntity noteEntity = noteRepository.findById(noteId).orElse(null);

        if (noteEntity != null) {
            noteEntity.setThumbsUpCount(noteEntity.getThumbsUpCount() + 1);
            noteEntity = noteRepository.save(noteEntity);
            
            UpdateNoteThumbsUpResponse response = UpdateNoteThumbsUpResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Thumbs up updated successfully")
                    .setThumbsUpCount(noteEntity.getThumbsUpCount())
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
        Long noteId = Long.parseLong(request.getNoteId());
        boolean exists = noteRepository.existsById(noteId);
        
        if (exists) {
            noteRepository.deleteById(noteId);
        }
        
        DeleteNoteResponse response = DeleteNoteResponse.newBuilder()
                .setSuccess(exists)
                .setMessage(exists ? "Note deleted successfully" : "Note not found")
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
