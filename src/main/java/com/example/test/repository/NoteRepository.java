package com.example.test.repository;
import com.example.test.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {}