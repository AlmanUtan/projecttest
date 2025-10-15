package com.idm3.CRUDExample.controllers;
import com.idm3.CRUDExample.model.Catalogue;
import com.idm3.CRUDExample.service.CatalogueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller()
@RequestMapping(value = {"/catalogues", "/catalogues/"})
public class CatalogueController {
    @Autowired
    private CatalogueService catalogueService;

    // Display all catalogues using ModelAndView
    @RequestMapping(value = {"/allcatalogues", ""})
    public ModelAndView listCatalogues() {
        ModelAndView mav = new ModelAndView("catalogue-list");
        mav.addObject("catalogues", catalogueService.findAllCatalogues());
        return mav; // Return ModelAndView with view name "catalogue-list" and model data
    }

    // Display products within a specific catalogue using ModelAndView
    @GetMapping("/productcatalogues/{id}")
    public ModelAndView viewCatalogue(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Optional<Catalogue> catalogue = catalogueService.findCatalogueById(id);
        if (catalogue.isPresent()) {
            mav.setViewName("catalogue-details");
            mav.addObject("catalogue", catalogue.get());
        } else {
            mav.setViewName("404"); // In case the catalogue is not found
        }
        return mav;
    }
}

