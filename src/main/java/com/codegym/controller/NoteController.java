package com.codegym.controller;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import com.codegym.service.NoteService;
import com.codegym.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

@Controller
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteTypeService noteTypeService;

    @ModelAttribute("noteTypes")
    public Page<NoteType> noteTypes(Pageable pageable) {
        return noteTypeService.findAll(pageable);
    }

    @GetMapping("/notes")
    public ModelAndView ListNote(@PageableDefault(size = 5) Pageable pageable) {
        Page<Note> notes = noteService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes",notes);
        return modelAndView;
    }

    @GetMapping("/create-note")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note",new Note());
        return modelAndView;
    }

    @PostMapping("/create-note")
    public ModelAndView createNote(@ModelAttribute Note note) {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("message","Create thành công");
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note",note);
        return modelAndView;
    }

    @GetMapping("/edit-note")
    public ModelAndView editNote(@ModelAttribute("note") Note note) {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("note", note);
        modelAndView.addObject("message", "Edit successfully");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/delete");
        modelAndView.addObject("note",note);
        return modelAndView;
    }

    @PostMapping("/delete-note")
    public ModelAndView deleteNote(@ModelAttribute Note note, Pageable pageable) {
        noteService.remove(note.getId());
        ModelAndView modelAndView = new ModelAndView("/note/list");
        Page<Note> notes = noteService.findAll(pageable);
        modelAndView.addObject("notes",notes);
        return modelAndView;

    }

    @GetMapping("/view-note/{id}")
    public ModelAndView viewNote(@PathVariable("id") Long id) {
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/note/view");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @GetMapping("/search-note")
    public ModelAndView searchByType(@RequestParam("noteType") String noteType,@PageableDefault(size = 5) Pageable pageable) {
        NoteType noteType1 = noteTypeService.findByName(noteType);
        Page<Note> notes = noteService.findByNoteType(noteType1, pageable);
        ModelAndView modelAndView = new ModelAndView("/note/search");
        modelAndView.addObject("notes",notes);
        return modelAndView;

    }

}
