package com.example.myServiceClient.service;

import com.example.lib.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyService {
    
    @GrpcClient("myService")
    private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

    public CreateNoteResponse createNote(String text, String username) {
        CreateNoteRequest request = CreateNoteRequest.newBuilder()
                .setText(text)
                .setUsername(username)
                .build();
        return myServiceStub.createNote(request);
    }

    public List<com.example.lib.Note> listNotes(boolean sortByPopularity) {
        ListNotesRequest request = ListNotesRequest.newBuilder()
            .setSortBy(sortByPopularity ? ListNotesRequest.SortBy.POPULAR : ListNotesRequest.SortBy.RECENT)
            .build();
        
        ListNotesResponse response = myServiceStub.listNotes(request);
        return response.getNotesList();
    }

    public UpdateNoteThumbsUpResponse updateNoteThumbsUp(String noteId) {
        UpdateNoteThumbsUpRequest request = UpdateNoteThumbsUpRequest.newBuilder()
                .setNoteId(noteId)
                .build();
        return myServiceStub.updateNoteThumbsUp(request);
    }

    public DeleteNoteResponse deleteNote(String noteId) {
        DeleteNoteRequest request = DeleteNoteRequest.newBuilder()
                .setNoteId(noteId)
                .build();
        return myServiceStub.deleteNote(request);
    }

    public GetNoteResponse getNote(String noteId) {
        GetNoteRequest request = GetNoteRequest.newBuilder()
                .setNoteId(noteId)
                .build();
        return myServiceStub.getNote(request);
    }
}
