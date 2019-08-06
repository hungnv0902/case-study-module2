package com.codegym.repository;

import com.codegym.model.Note;
import com.codegym.model.NoteType;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    Page<Note> findAllByNoteType(NoteType noteType, Pageable pageable);
}
