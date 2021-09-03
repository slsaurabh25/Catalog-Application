package com.example.catalogapplication.demo.service;

import com.example.catalogapplication.demo.dao.AttributeRepository;
import com.example.catalogapplication.demo.dao.CategoryRepository;
import com.example.catalogapplication.demo.dao.ProductRepository;
import com.example.catalogapplication.demo.model.Attribute;
import com.example.catalogapplication.demo.model.Category;
import com.example.catalogapplication.demo.model.Product;
import com.example.catalogapplication.demo.wrapper.AttributeWrapper;
import com.example.catalogapplication.demo.wrapper.CategoryWrapper;
import com.example.catalogapplication.demo.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class CatalogService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AttributeRepository attributeRepository;

    public int createCategory(Category category) {
        Category category1 = categoryRepository.save(category);
        return category1.getId();
    }

    public int createAttributeForCategory(int id, Attribute attribute) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Attribute attribute1 = new Attribute();
            attribute1.setName(attribute.getName());
            attribute1.setValue(attribute.getValue());

            Category category =categoryOptional.get();
            attribute1.setCategory(category);
            attributeRepository.save(attribute1);

            if (CollectionUtils.isEmpty(category.getAttributes())) {
                category.setAttributes(new HashSet<>());
            }
            category.getAttributes().add(attribute1);
            categoryRepository.save(category);

            return attribute1.getId();
        } else {
            throw new Exception("Category doesn't Exist");
        }
    }

    public int createProduct(int categoryId, Product product) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {

            Product tempProduct = new Product();
            tempProduct.setName(product.getName());

            Category category = categoryOptional.get();
            tempProduct.setCategory(category);
            productRepository.save(tempProduct);

            if (CollectionUtils.isEmpty(category.getProducts())) {
                category.setProducts(new HashSet<>());
            }
            category.getProducts().add(tempProduct);
            categoryRepository.save(category);

            return tempProduct.getId();

        } else {
            throw new Exception("Category doesn't Exist");
        }
    }

    public ProductWrapper getProductById(int id) throws Exception {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductWrapper productWrapper = ProductWrapper.builder()
                            .productId(product.getId())
                                    .productName(product.getName())
                                            .categoryId(product.getCategory().getId())
                                                    .attributes(buildAttributes(product.getCategory(), product.getAttributes())).build();

            return productWrapper;
        } else {
            throw new Exception("Record Not Found");
        }
    }

    private List<AttributeWrapper> buildAttributes(Category category, Set<Attribute> attributes) {
        List<AttributeWrapper> attributeWrappers = new ArrayList<>();

        if (category!=null) {
            if(!CollectionUtils.isEmpty(category.getAttributes()))   {
                for (Attribute attribute:category.getAttributes()) {
                    AttributeWrapper attributeWrapper = AttributeWrapper.builder()
                            .attributeId(attribute.getId())
                            .attributeName(attribute.getName())
                            .attributeValue(attribute.getValue())
                            .build();
                    attributeWrappers.add(attributeWrapper);
                }
            }
        }

        if(!CollectionUtils.isEmpty(attributes))   {
            for (Attribute attribute:attributes) {
                AttributeWrapper attributeWrapper = AttributeWrapper.builder()
                        .attributeId(attribute.getId())
                        .attributeName(attribute.getName())
                        .attributeValue(attribute.getValue())
                        .build();
                attributeWrappers.add(attributeWrapper);
            }
        }

        return attributeWrappers;
    }

    public CategoryWrapper getCategoryById(int id) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {

            Category category = categoryOptional.get();
            CategoryWrapper categoryWrapper = CategoryWrapper.builder()
                    .categoryId(category.getId())
                    .categoryName(category.getName())
                    .attributes(buildAttributes(category, null)).build();

            return categoryWrapper;
        } else {
            throw new Exception("Record Not Found");
        }
    }
}
