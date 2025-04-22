package com.example.myServiceClient.service;

import com.example.lib.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

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

    public ListNotesResponse listNotes() {
        ListNotesRequest request = ListNotesRequest.newBuilder().build();
        return myServiceStub.listNotes(request);
    }
}
