package com.example.catalogapplication.demo.dao;

import com.example.catalogapplication.demo.model.Attribute;
import org.springframework.data.repository.CrudRepository;

public interface AttributeRepository extends CrudRepository<Attribute, Integer> {
}
