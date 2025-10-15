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

@Service
public class SavedProjectService {

    private static final Long FIXED_EMPLOYER_ID = 3L; // single employer for prototype

    @Autowired
    private SavedProjectRepository savedProjectRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<SavedProject> listForEmployer() {
        return savedProjectRepository.findByEmployerId(FIXED_EMPLOYER_ID);
    }

    public SavedProject getById(Long id) {
        return savedProjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SavedProject not found: " + id));
    }

    public SavedProject create(Long projectId, String notes, String status) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + projectId));

        SavedProject sp = new SavedProject();
        sp.setEmployerId(FIXED_EMPLOYER_ID);
        sp.setProject(project);
        sp.setDate(LocalDate.now());
        sp.setLastViewed(null);
        sp.setNotes(notes);
        sp.setStatus(status);

        return savedProjectRepository.save(sp);
    }

    public SavedProject update(Long id, Long projectId, String notes, String status) {
        SavedProject sp = getById(id);
        if (!sp.getEmployerId().equals(FIXED_EMPLOYER_ID)) {
            throw new EntityNotFoundException("SavedProject does not belong to employer");
        }
        if (projectId != null && !projectId.equals(sp.getProject().getProjectId())) {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new EntityNotFoundException("Project not found: " + projectId));
            sp.setProject(project);
        }
        sp.setNotes(notes);
        sp.setStatus(status);
        return savedProjectRepository.save(sp);
    }

    public void delete(Long id) {
        SavedProject sp = getById(id);
        if (!sp.getEmployerId().equals(FIXED_EMPLOYER_ID)) {
            throw new EntityNotFoundException("SavedProject does not belong to employer");
        }
        savedProjectRepository.delete(sp);
    }

    public SavedProject markViewed(Long id) {
        SavedProject sp = getById(id);
        sp.setLastViewed(LocalDateTime.now());
        return savedProjectRepository.save(sp);
    }

    public List<Project> allProjects() {
        return projectRepository.findAll();
    }
}