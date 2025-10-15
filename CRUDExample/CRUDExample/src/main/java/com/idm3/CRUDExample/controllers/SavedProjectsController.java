package com.idm3.CRUDExample.controllers;

import com.idm3.CRUDExample.model.SavedProject;
import com.idm3.CRUDExample.service.SavedProjectService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/employer/saved-projects", "/employer/saved-projects/"})
public class SavedProjectsController {

    @Autowired
    private SavedProjectService savedProjectService;

    @GetMapping({"", "/"})
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("savedprojects-list");
        mav.addObject("savedProjects", savedProjectService.listForEmployer());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        ModelAndView mav = new ModelAndView("savedprojects-add");
        mav.addObject("projects", savedProjectService.allProjects());
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView add(
            @RequestParam("projectId") @NotNull Long projectId,
            @RequestParam(value = "notes", required = false) String notes,
            @RequestParam(value = "status", required = false) String status
    ) {
        savedProjectService.create(projectId, notes, status);
        return new ModelAndView("redirect:/employer/saved-projects");
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