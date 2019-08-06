package com.codegym.service;

import com.codegym.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteTypeService {
    Page<NoteType> findAll(Pageable pageable);
    void save(NoteType noteType);
    void remove(Long id);
    NoteType findByName(String name);
    NoteType findById(Long id);
}
