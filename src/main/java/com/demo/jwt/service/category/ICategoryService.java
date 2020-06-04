package com.demo.jwt.service.category;

import com.demo.jwt.model.product.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();

    Optional<Category> findById(String id);

    void remove(Category category);

    void add(Category category);
}
