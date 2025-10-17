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
@RequestMapping(value = {"/saved", "/saved/"})
public class SavedProjectsController {

    @Autowired
    private SavedProjectService productService;

    @RequestMapping(value = {"/allProducts", ""})
    public ModelAndView displayAllProducts() {
        return new ModelAndView("/savedprojects-list", "products", productService.findAllProducts());
    }
    @GetMapping("/add")
    public ModelAndView showAddProductForm() {
        return new ModelAndView("/savedprojects-add", "newProduct", new SavedProject());
    }
    @PostMapping("/addProduct")
    public ModelAndView addABook(@ModelAttribute("newProduct") SavedProject x,
                                 @Valid SavedProject product, BindingResult result,
                                 @RequestParam("productImageFile") MultipartFile file) {

        // Check if there are validation errors
        if (result.hasErrors()) {
            return new ModelAndView("/savedprojects-add", "newProduct", product)
                    .addObject("errors", result.getAllErrors());
        }

        else {


            return new ModelAndView("redirect:/product/allProducts");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable("id") long id) {
        if (productService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Product not found");
        else
            return new ModelAndView("/savedprojects-edit", "aProduct", productService.findOne(id).get());
    }

    @PostMapping("/saveProduct")
    public ModelAndView saveOrUpdateProduct(@ModelAttribute("aProduct") SavedProject p, BindingResult result) {
        System.out.println("product id " + p.getId() );
        //if (result.hasErrors()) {
        //   String viewName = (p.getId() == null) ? "/addProduct" : "/editProduct";
        // return new ModelAndView(viewName);
        //}

        if (p.getId() == null) {
            productService.saveProduct(p);
        } else {
            productService.updateProduct(p);
        }

        return new ModelAndView("redirect:/product");
    }






    @PostMapping("/delete")
    public ModelAndView deleteProduct(@RequestParam("productId") long id) {
        if (productService.findOne(id).isEmpty()){
            return new ModelAndView("/error", "error", "Product not found");
        } else {
            productService.deleteByID(id);
            return new ModelAndView("redirect:/product/");
        }
    }



}


