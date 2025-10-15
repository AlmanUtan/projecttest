package com.idm3.CRUDExample.service;

import com.idm3.CRUDExample.model.Products;
import com.idm3.CRUDExample.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
@Service


public class ProductService {

    @Autowired
    private ProductRepository productRepository; // Retrieve all products
    private static final String UPLOAD_DIR = "src/main/resources/static/assets/images/";
    public List<Products> findAllProducts() {
        return productRepository.findAll();

    }
    public void saveProduct(Products a) {
        productRepository.save(a);
    }



    public Optional<Products> findOne(long productId) {
        return productRepository.findById(productId);
    }

    public void deleteByID(long productId) {
        productRepository.deleteById(productId);
    }

    public Products updateProduct(Products updatedProduct) {
        System.out.println("repo *********" + updatedProduct.getProductId());
        Optional<Products> existingProduct = productRepository.findById(updatedProduct.getProductId());

        if (existingProduct.isPresent()) {
            Products product = existingProduct.get();
            product.setProductName(updatedProduct.getProductName());
            product.setProductDescription(updatedProduct.getProductDescription());
            product.setProductImage(updatedProduct.getProductImage());
            product.setListPrice(updatedProduct.getListPrice());
            product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setWholeSalePrice(updatedProduct.getWholeSalePrice());
            productRepository.save(product);
            return product;
        } else {
            throw new EntityNotFoundException("Product not found with id " + updatedProduct.getProductId());
        }
    }


    public void saveProductWithImage(Products product, MultipartFile file) throws IOException {
        String filename = "";
        if (!file.isEmpty()) {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            filename = file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + filename);
            Files.write(filePath, file.getBytes());
            product.setProductImage(filename);
        }
        productRepository.save(product);
    }





}





