package com.codegym.controller;
import java.security.Principal;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import com.codegym.service.NoteService;
import com.codegym.service.NoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteTypeService noteTypeService;

    @ModelAttribute("noteTypes")
    public Page<NoteType> noteTypes(Pageable pageable) {
        return noteTypeService.findAll(pageable);
    }

    @GetMapping("/")
    public String index() {
        return "/user/index";
    }

    @GetMapping("/user")
    public ModelAndView user(Principal principal, Pageable pageable) {
        // Get authenticated user name from Principal
        Page<Note> notes = noteService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes",notes);

        System.out.println(principal.getName());
       return modelAndView;
    }


}
