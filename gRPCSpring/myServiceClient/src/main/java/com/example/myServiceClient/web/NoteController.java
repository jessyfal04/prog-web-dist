package com.example.myServiceClient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Controller
public class NoteController {

    private final WebService webService;

    @Autowired
    public NoteController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("/")
    public String showNotesList() {
        return "list-notes";
    }

    @GetMapping("/create")
    public String showCreateNoteForm() {
        return "create-note";
    }

    @GetMapping("/notes")
    public String redirectToList() {
        return "redirect:/";
    }

    @GetMapping("/notes/{id}")
    public String showNote(@PathVariable String id, Model model) {
        try {
            WebService.NoteDTO note = webService.getNote(id);
            model.addAttribute("note", note);
            return "view-note";
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving note", e);
        }
    }
} 