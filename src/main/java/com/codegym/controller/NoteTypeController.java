package com.codegym.controller;

import com.codegym.model.NoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import com.codegym.service.NoteService;
import com.codegym.service.NoteTypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteTypeController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteTypeService noteTypeService;

    @GetMapping("/noteTypes")
    public String listDepartments(Pageable pageable, Model model){
        Page<NoteType> noteTypes = noteTypeService.findAll(pageable);
        model.addAttribute("noteTypes", noteTypes);
        return "/noteType/list";
    }

    @GetMapping("/create-type")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        modelAndView.addObject("noteType", new NoteType());
        return modelAndView;
    }

    @PostMapping("create-type")
    public ModelAndView createType(@ModelAttribute("noteType") NoteType noteType) {
        noteTypeService.save(noteType);
        ModelAndView modelAndView = new ModelAndView("/noteType/create");
        modelAndView.addObject("message","Create successfully");
        modelAndView.addObject("noteType",noteType);
        return modelAndView;
    }

    @GetMapping("/edit-noteType/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        NoteType noteType = noteTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/noteType/edit");
        modelAndView.addObject("noteType",noteType);
        return modelAndView;
    }

    @PostMapping("/edit-noteType")
    public ModelAndView editNoteType(@ModelAttribute("noteType") NoteType noteType) {
        noteTypeService.save(noteType);
        ModelAndView modelAndView = new ModelAndView("/noteType/edit");
        modelAndView.addObject("noteType",noteType);
        modelAndView.addObject("message", "Edit successfully");
        return modelAndView;
    }

    @GetMapping("/delete-noteType/{id}")
    public ModelAndView deleteFormNoteType(@PathVariable Long id) {
        NoteType noteType = noteTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/noteType/delete");
        modelAndView.addObject("noteType",noteType);
        return modelAndView;

    }

    @PostMapping("/delete-noteType")
    public ModelAndView deleteNoteType(@ModelAttribute("noteType") NoteType noteType) {
        noteTypeService.remove(noteType.getId());
        ModelAndView modelAndView = new ModelAndView("/noteType/delete");
        modelAndView.addObject("message", "Note Type delete successfully");
        return modelAndView;
    }

}
