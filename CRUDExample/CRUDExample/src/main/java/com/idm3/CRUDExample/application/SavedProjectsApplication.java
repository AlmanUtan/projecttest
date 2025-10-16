package com.idm3.CRUDExample.application;

import com.idm3.CRUDExample.model.SavedProject;

import com.idm3.CRUDExample.service.SavedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



import java.util.List;


@ComponentScan({"com.idm3.CRUDExample.service", "com.idm3.CRUDExample.controllers"})
@EntityScan("com.idm3.CRUDExample.model")
@EnableJpaRepositories("com.idm3.CRUDExample.repository")
@SpringBootApplication(scanBasePackages = "com.idm3.CRUDExample")
public class SavedProjectsApplication implements CommandLineRunner {
    @Autowired
    private SavedProjectService savedProjectService; // Inject ProductService
    public static void main(String[] args) {
        SpringApplication.run(SavedProjectsApplication.class, args); // Starts the Spring Boot application
    }
    @Override
    public void run(String... args) throws Exception {
        // Test the findAllProducts method
        List<SavedProject> products = savedProjectService.listForEmployer();
        // Print the products to the console
        System.out.println("List of Products:");
        for (SavedProject product : products) { System.out.println(product.getProject());
            // Print each product's details
        }
    }
}