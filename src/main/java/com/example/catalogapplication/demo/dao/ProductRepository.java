package com.example.catalogapplication.demo.dao;

import com.example.catalogapplication.demo.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
