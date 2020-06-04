package com.demo.jwt.controller;

import com.demo.jwt.model.product.Category;
import com.demo.jwt.service.category.CategoryService;
import com.demo.jwt.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private ICategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategoryList() {
        List<Category> categories = this.categoryService.findAll();
        if (!categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        this.categoryService.add(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/remove/{cid}")
    public ResponseEntity<Void> removeCategory(@PathVariable("cid") String cid) {
        Optional<Category> category = categoryService.findById(cid);
        if (category.isPresent()) {
            categoryService.remove(category.get());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
