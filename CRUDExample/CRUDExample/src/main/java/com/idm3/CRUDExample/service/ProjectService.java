package com.idm3.CRUDExample.service;

import com.idm3.CRUDExample.model.Project;
import com.idm3.CRUDExample.model.SavedProject;
import com.idm3.CRUDExample.repository.ProjectRepository;
import com.idm3.CRUDExample.repository.SavedProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ProjectService {




    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAllProject() {
        return projectRepository.findAll();
    }

    public Optional<Project> findProjectById(Long id) {
        return projectRepository.findById(id);
    }
}