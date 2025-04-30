package com.example.myServiceClient.web;

import com.example.myServiceClient.service.MyService;
import com.example.lib.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return new NoteDTO(response.getNoteId(), text, username, response.getMessage(), 0);
    }

    @GetMapping("/api/notes")
    @ResponseStatus(HttpStatus.OK)
    public List<NoteDTO> listNotes() {
        ListNotesResponse response = myService.listNotes();
        return response.getNotesList().stream()
                .map(note -> new NoteDTO(note.getId(), note.getText(), note.getUsername(), note.getCreatedAt(), note.getThumbsUpCount()))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/notes/{noteId}/thumbs-up")
    @ResponseStatus(HttpStatus.OK)
    public ThumbsUpDTO updateThumbsUp(@PathVariable String noteId) {
        UpdateNoteThumbsUpResponse response = myService.updateNoteThumbsUp(noteId);
        return new ThumbsUpDTO(response.getSuccess(), response.getMessage(), response.getThumbsUpCount());
    }

    @DeleteMapping("/api/notes/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteResponseDTO deleteNote(@PathVariable String noteId) {
        DeleteNoteResponse response = myService.deleteNote(noteId);
        return new DeleteResponseDTO(response.getSuccess(), response.getMessage());
    }

    @GetMapping("/api/notes/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDTO getNote(@PathVariable String noteId) {
        GetNoteResponse response = myService.getNote(noteId);
        if (!response.getSuccess()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, response.getMessage());
        }
        com.example.lib.Note note = response.getNote();
        return new NoteDTO(note.getId(), note.getText(), note.getUsername(), note.getCreatedAt(), note.getThumbsUpCount());
    }

    @GetMapping("/api/notes/{noteId}/markdown")
    @ResponseStatus(HttpStatus.OK)
    public String exportNoteToMarkdown(@PathVariable String noteId) {
        GetNoteResponse response = myService.getNote(noteId);
        if (!response.getSuccess()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, response.getMessage());
        }
        com.example.lib.Note note = response.getNote();
        return String.format("# Note by %s\n\n%s\n\nCreated at: %s\nThumbs up: %d",
                note.getUsername(),
                note.getText(),
                note.getCreatedAt(),
                note.getThumbsUpCount());
    }

    public static class NoteDTO {
        private final String id;
        private final String text;
        private final String username;
        private final String createdAt;
        private final int thumbsUpCount;

        public NoteDTO(String id, String text, String username, String createdAt, int thumbsUpCount) {
            this.id = id;
            this.text = text;
            this.username = username;
            this.createdAt = createdAt;
            this.thumbsUpCount = thumbsUpCount;
        }

        public String getId() {
            return id;
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

        public int getThumbsUpCount() {
            return thumbsUpCount;
        }
    }

    public static class ThumbsUpDTO {
        private final boolean success;
        private final String message;
        private final int thumbsUpCount;

        public ThumbsUpDTO(boolean success, String message, int thumbsUpCount) {
            this.success = success;
            this.message = message;
            this.thumbsUpCount = thumbsUpCount;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public int getThumbsUpCount() {
            return thumbsUpCount;
        }
    }

    public static class DeleteResponseDTO {
        private final boolean success;
        private final String message;

        public DeleteResponseDTO(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
