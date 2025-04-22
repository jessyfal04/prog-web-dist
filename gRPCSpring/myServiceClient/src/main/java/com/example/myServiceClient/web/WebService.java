package com.example.myServiceClient.web;

import com.example.myServiceClient.service.MyService;
import com.example.lib.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebService {

    private final MyService myService;

    @Autowired
    public WebService(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("/api/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO createNote(@RequestParam String text, @RequestParam String username) {
        CreateNoteResponse response = myService.createNote(text, username);
        return new NoteDTO(text, username, response.getMessage());
    }

    @GetMapping("/api/notes")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDTO> listNotes() {
        ListNotesResponse response = myService.listNotes();
        return response.getNotesList().stream()
                .map(note -> new NoteDTO(note.getText(), note.getUsername(), note.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public static class NoteDTO {
        private final String text;
        private final String username;
        private final String createdAt;

        public NoteDTO(String text, String username, String createdAt) {
            this.text = text;
            this.username = username;
            this.createdAt = createdAt;
        }

        public String getText() {
            return text;
        }

        public String getUsername() {
            return username;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }
}
