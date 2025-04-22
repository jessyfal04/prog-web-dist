package com.example.myServiceClient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoteController {

    @GetMapping("/")
    public String showCreateNoteForm() {
        return "create-note";
    }

    @GetMapping("/notes")
    public String showNotesList() {
        return "list-notes";
    }
} 