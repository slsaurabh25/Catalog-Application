package com.example.catalogapplication.demo.dao;

import com.example.catalogapplication.demo.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
