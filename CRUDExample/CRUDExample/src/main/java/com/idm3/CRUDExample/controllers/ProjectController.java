package com.idm3.CRUDExample.controllers;
import com.idm3.CRUDExample.model.Project;
import com.idm3.CRUDExample.service.ProjectService;

import com.idm3.CRUDExample.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller()
@RequestMapping(value = {"/projects", "/projects/"})
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // Display all catalogues using ModelAndView
    @RequestMapping(value = {"/allprojects", ""})
    public ModelAndView listCatalogues() {
        ModelAndView mav = new ModelAndView("catalogue-list");
        mav.addObject("catalogues", projectService.findAllProject());
        return mav; // Return ModelAndView with view name "catalogue-list" and model data
    }

    // Display products within a specific catalogue using ModelAndView
    @GetMapping("/productcatalogues/{id}")
    public ModelAndView viewCatalogue(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Optional<Project> project = projectService.findProjectById(id);
        if (project.isPresent()) {
            mav.setViewName("catalogue-details");
            mav.addObject("catalogue", project.get());
        } else {
            mav.setViewName("404"); // In case the catalogue is not found
        }
        return mav;
    }
}