package com.idm3.CRUDExample;

import com.idm3.CRUDExample.repository.ProjectRepository;
import com.idm3.CRUDExample.repository.SavedProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SavedProjectsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SavedProjectsApplication.class, args);
    }

    // Optional: simple startup check to verify DB connectivity and table counts
    @Bean
    CommandLineRunner verifyDatabase(ProjectRepository projectRepository,
                                     SavedProjectRepository savedProjectRepository) {
        return args -> {
            try {
                long projectCount = projectRepository.count();
                long savedCount = savedProjectRepository.count();
                System.out.println("DB OK: projects=" + projectCount + ", savedProjects=" + savedCount);
            } catch (Exception e) {
                System.err.println("DB connection problem: " + e.getMessage());
            }
        };
    }
}