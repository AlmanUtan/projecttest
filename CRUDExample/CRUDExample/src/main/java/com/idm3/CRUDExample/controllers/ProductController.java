package com.idm3.CRUDExample.controllers;

import com.idm3.CRUDExample.model.Products;
import com.idm3.CRUDExample.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller()
@RequestMapping(value = {"/product", "/product/"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/allProducts", ""})
    public ModelAndView displayAllProducts() {
        return new ModelAndView("/viewAll", "products", productService.findAllProducts());
    }
    @GetMapping("/add")
    public ModelAndView showAddProductForm() {
        return new ModelAndView("/addProduct", "newProduct", new Products());
    }
    @PostMapping("/addProduct")
    public ModelAndView addABook(@ModelAttribute("newProduct") Products x,
                                 @Valid Products product, BindingResult result,
                                 @RequestParam("productImageFile") MultipartFile file) {

        // Check if there are validation errors
        if (result.hasErrors()) {
            return new ModelAndView("/addProduct", "newProduct", product)
                    .addObject("errors", result.getAllErrors());
        }

        else {

            try {
                productService.saveProductWithImage(x, file);
            } catch (IOException e) {
                e.printStackTrace();
                return new ModelAndView("/error", "error", "Image upload failed");
            }
            return new ModelAndView("redirect:/product/allProducts");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable("id") long id) {
        if (productService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Product not found");
        else
            return new ModelAndView("/editProduct", "aProduct", productService.findOne(id).get());
    }

    @PostMapping("/saveProduct")
    public ModelAndView saveOrUpdateProduct(@ModelAttribute("aProduct") Products p, BindingResult result) {
        System.out.println("product id " + p.getProductId() );
       //if (result.hasErrors()) {
         //   String viewName = (p.getId() == null) ? "/addProduct" : "/editProduct";
           // return new ModelAndView(viewName);
        //}

        if (p.getProductId() == null) {
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

