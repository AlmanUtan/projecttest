package com.idm3.CRUDExample.model;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")

public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")

    @Column(name = "product_name", length = 255)
    private String productName;

    @NotBlank(message = "Product description is required")
    @Size(max = 255, message = "Product description must not exceed 255 characters")

    @Column(name = "product_description", length = 255)
    private String productDescription;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be at least 0")

    @Column(name = "stock_quantity")
    private Long stockQuantity;

    @NotNull(message = "Wholesale price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Wholesale price must be greater than 0")

    @Column(name = "whole_sale_price", nullable = false)
    private Double wholeSalePrice;

    @NotNull(message = "List price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "List price must be greater than 0")

    @Column(name = "list_price", nullable = false)
    private Double listPrice;

    @Column(name = "product_image", length = 255)
    private String productImage;


    // we can put in this code it will allow a user to pick up all catalogues where the product is found
//will not need it for the current use case
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCatalogue> productCatalogues;


}
