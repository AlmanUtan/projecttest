package com.idm3.CRUDExample.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "savedprojects")
public class SavedProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    // Single employer scenario: keep as a plain Long for now
    @Column(name = "EmployerID", nullable = false)
    private Long employerId;

    // Link to Project table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectID", nullable = false)
    private Project project;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "lastViewed")
    private LocalDateTime lastViewed;

    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "status", length = 255)
    private String status;
}