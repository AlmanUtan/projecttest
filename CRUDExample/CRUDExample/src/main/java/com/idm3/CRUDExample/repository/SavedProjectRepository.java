package com.idm3.CRUDExample.repository;

import com.idm3.CRUDExample.model.SavedProject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface SavedProjectRepository extends JpaRepository<SavedProject, Long> {
    @Service
    public class ProductService {

        @Autowired
        private SavedProjectRepository productRepo;

        public Optional<SavedProject> findOne(long id) {
            return productRepo.findById(id);
        }

        public SavedProject updateProduct(SavedProject updatedProduct) {
            Optional<SavedProject> existingProduct = productRepo.findById(updatedProduct.getId());

            if (existingProduct.isPresent()) {
                SavedProject product = existingProduct.get();
                product.setProjectId(updatedProduct.getProjectId());

                product.setDate(updatedProduct.getDate());
                product.setLastViewed(updatedProduct.getLastViewed());

                product.setStatus(updatedProduct.getStatus());
                productRepo.save(product);
                return product;
            } else {
                throw new EntityNotFoundException("Product not found with id " + updatedProduct.getId());
            }
        }

        public void saveProduct(SavedProject product) {
            productRepo.save(product);
        }
    }

}
