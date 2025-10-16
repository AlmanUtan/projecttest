package com.idm3.CRUDExample.controllers;

import com.idm3.CRUDExample.model.SavedProject;
import com.idm3.CRUDExample.service.SavedProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller()
@RequestMapping({"/employers", "/employer"})
public class SavedProjectsController {

    @Autowired
    private SavedProjectService savedProjectService;

    @RequestMapping(value = {"/allSavedProjects", ""})
    public ModelAndView displayAllSavedProjects() {
        return new ModelAndView("/savedprojects-list", "savedProjects", savedProjectService.listForEmployer());
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("savedprojects-add");
        mav.addObject("projects", savedProjectService.allProjects());
        return mav;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("savedprojects-edit");
        SavedProject sp = savedProjectService.getById(id);
        mav.addObject("savedProject", sp);
        mav.addObject("projects", savedProjectService.allProjects());
        return mav;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            @RequestParam("projectId") Long projectId,
            @RequestParam(value = "notes", required = false) String notes,
            @RequestParam(value = "status", required = false) String status
    ) {
        savedProjectService.update(id, projectId, notes, status);
        return new ModelAndView("redirect:/employer/saved-projects");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long id) {
        savedProjectService.delete(id);
        return new ModelAndView("redirect:/employer/saved-projects");
    }

    @PostMapping("/view/{id}")
    public ModelAndView markViewed(@PathVariable Long id) {
        savedProjectService.markViewed(id);
        return new ModelAndView("redirect:/employer/saved-projects");
    }
}