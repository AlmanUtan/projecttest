package com.idm3.CRUDExample.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "savedProjects")
public class SavedProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // matches SQL: ID bigint PK AUTO_INCREMENT
    private Long id;

    @NotNull(message = "EmployerID is required")
    @Column(name = "EmployerID", nullable = false)
    private Long employerId;

    @NotNull(message = "ProjectID is required")
    @Column(name = "ProjectID", nullable = false)
    private Long projectId;

    // Let DB default (current_timestamp) populate this on insert.
    // insertable=false ensures Hibernate doesn't send NULL explicitly.
    @Column(name = "date", insertable = false, updatable = false)
    private LocalDate date;

    @Column(name = "lastViewed")
    private LocalDateTime lastViewed;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    @Column(name = "notes", length = 500)
    private String notes;

    @Size(max = 255, message = "Status must not exceed 255 characters")
    @Column(name = "status", length = 255)
    private String status;
}