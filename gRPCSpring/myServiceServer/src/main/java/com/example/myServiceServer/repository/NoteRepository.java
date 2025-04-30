package com.example.myServiceServer.repository;

import com.example.myServiceServer.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findAllByOrderByThumbsUpCountDesc();
    List<NoteEntity> findAllByOrderByCreatedAtDesc();
} 