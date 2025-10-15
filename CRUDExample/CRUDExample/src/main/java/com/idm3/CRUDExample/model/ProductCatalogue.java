package com.idm3.CRUDExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import com.idm3.CRUDExample.model.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "product_catalogue")
public class ProductCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "catalogue_id", nullable = false)
    private Catalogue catalogue;
}
