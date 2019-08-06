package com.codegym.service.Impl;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import com.codegym.repository.NoteRepository;
import com.codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void remove(Long id) {
        noteRepository.delete(id);
    }

    @Override
    public Page<Note> findByNoteType(NoteType noteType, Pageable pageable) {
        return noteRepository.findAllByNoteType(noteType, pageable);
    }

    @Override
    public Note findById(Long id) {
        return noteRepository.findOne(id);
    }
}
