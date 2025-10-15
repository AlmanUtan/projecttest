package com.idm3.CRUDExample.repository;

import com.idm3.CRUDExample.model.Products;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    @Service
    public class ProductService {

        @Autowired
        private ProductRepository productRepo;

        public Optional<Products> findOne(long id) {
            return productRepo.findById(id);
        }

        public Products updateProduct(Products updatedProduct) {
            Optional<Products> existingProduct = productRepo.findById(updatedProduct.getProductId());

            if (existingProduct.isPresent()) {
                Products product = existingProduct.get();
                product.setProductName(updatedProduct.getProductName());
                product.setProductDescription(updatedProduct.getProductDescription());
                product.setProductImage(updatedProduct.getProductImage());
                product.setListPrice(updatedProduct.getListPrice());
                product.setStockQuantity(updatedProduct.getStockQuantity());
                product.setWholeSalePrice(updatedProduct.getWholeSalePrice());
                productRepo.save(product);
                return product;
            } else {
                throw new EntityNotFoundException("Product not found with id " + updatedProduct.getProductId());
            }
        }

        public void saveProduct(Products product) {
            productRepo.save(product);
        }
    }

}
