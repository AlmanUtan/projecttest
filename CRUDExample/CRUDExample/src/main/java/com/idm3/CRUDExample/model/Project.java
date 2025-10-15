package com.idm3.CRUDExample.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectID")
    private Long projectId;

    @Column(name = "ProjectName", length = 255)
    private String projectName;

    @Column(name = "ProjectDescription", length = 500, nullable = false)
    private String projectDescription;

    @Column(name = "UserID")
    private Long userId; // keep simple for now

    @Column(name = "Category", length = 255)
    private String category;

    @Column(name = "ProjectDescriptionSummary", length = 255)
    private String projectDescriptionSummary;

    @Column(name = "ProjectHeroImage", length = 255)
    private String projectHeroImage;

    @Column(name = "CreationDate", nullable = false)
    private LocalDate creationDate;

    @Column(name = "additionalDoc", length = 255)
    private String additionalDoc;
}