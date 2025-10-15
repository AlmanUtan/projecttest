package com.idm3.CRUDExample.repository;

import com.idm3.CRUDExample.model.SavedProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedProjectRepository extends JpaRepository<SavedProject, Long> {
    List<SavedProject> findByEmployerId(Long employerId);
}