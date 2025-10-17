package com.idm3.CRUDExample.service;

import com.idm3.CRUDExample.model.SavedProject;

import com.idm3.CRUDExample.repository.SavedProjectRepository;
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


public class SavedProjectService {

    @Autowired
    private SavedProjectRepository productRepository; // Retrieve all products
    private static final String UPLOAD_DIR = "src/main/resources/static/assets/images/";
    public List<SavedProject> findAllProducts() {
        return productRepository.findAll();

    }
    public void saveProduct(SavedProject a) {
        productRepository.save(a);
    }



    public Optional<SavedProject> findOne(long productId) {
        return productRepository.findById(productId);
    }

    public void deleteByID(long productId) {
        productRepository.deleteById(productId);
    }

    public SavedProject updateProduct(SavedProject updatedProduct) {
        System.out.println("repo *********" + updatedProduct.getId());
        Optional<SavedProject> existingProduct = productRepository.findById(updatedProduct.getId());

        if (existingProduct.isPresent()) {
            SavedProject product = existingProduct.get();
            product.setProjectId(updatedProduct.getProjectId());

            product.setDate(updatedProduct.getDate());
            product.setLastViewed(updatedProduct.getLastViewed());

            product.setStatus(updatedProduct.getStatus());
            productRepository.save(product);
            return product;
        } else {
            throw new EntityNotFoundException("Product not found with id " + updatedProduct.getId());
        }
    }








}





