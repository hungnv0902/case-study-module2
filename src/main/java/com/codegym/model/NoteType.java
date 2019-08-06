package com.codegym.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "noteType")
public class NoteType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(targetEntity = Note.class)
    private List<Note> notes;

    public NoteType() {
    }

    public NoteType(String name, List<Note> notes) {
        this.name = name;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
