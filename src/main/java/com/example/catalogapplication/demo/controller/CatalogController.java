package com.example.catalogapplication.demo.controller;

import com.example.catalogapplication.demo.model.Attribute;
import com.example.catalogapplication.demo.model.Category;
import com.example.catalogapplication.demo.model.Product;
import com.example.catalogapplication.demo.service.CatalogService;
import com.example.catalogapplication.demo.wrapper.CategoryWrapper;
import com.example.catalogapplication.demo.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @PostMapping("/category")
    private int createCategory(@RequestBody Category category) {
        return catalogService.createCategory(category);
    }

    @PostMapping("/category/{id}")
    private int createAttributeForCategory(@PathVariable("id") int id,
                               @RequestBody Attribute category) throws Exception {
        return catalogService.createAttributeForCategory(id, category);
    }

    @PostMapping("/product")
    private int createProduct(@RequestHeader("categoryId") int categoryId,
                                  @RequestBody Product product) throws Exception {
        return catalogService.createProduct(categoryId, product);
    }

    @GetMapping("/product/{id}")
    private ProductWrapper getProduct(@PathVariable("id") int id) throws Exception {
        return catalogService.getProductById(id);
    }

    @GetMapping("/category/{id}")
    private CategoryWrapper getCategory(@PathVariable("id") int id) throws Exception {
        return catalogService.getCategoryById(id);
    }

}
