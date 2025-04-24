package com.example.myServiceServer.repository;

import com.example.myServiceServer.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
} 