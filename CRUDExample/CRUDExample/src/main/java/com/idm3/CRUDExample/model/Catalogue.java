package com.idm3.CRUDExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import com.idm3.CRUDExample.model.Products;
import java.util.List;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "catalogue")
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalogue_id")
    private Long catalogueId;

    @Column(name = "catalogue_name", nullable = false)
    private String catalogueName;

    @Column(name = "catalogue_description")
    private String catalogueDescription;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "catalogue_image")
    private String catalogueImage;

    @Column(name = "created_by")
    private String createdBy;

    @OneToMany(mappedBy = "catalogue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCatalogue> productCatalogues;
}

