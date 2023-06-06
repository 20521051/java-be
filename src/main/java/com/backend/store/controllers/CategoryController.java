package com.backend.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.backend.store.services.CloudinaryService;
import com.backend.store.services.*;
import com.backend.store.models.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getMany();
    }

    @PostMapping
    public ResponseDTO createCategory(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        return categoryService.create(name, file);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateCategory(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, name = "file") MultipartFile file
    ) {
        return categoryService.update(id, name, file);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteCategory(@PathVariable String id) {
        return categoryService.delete(id);
    }
}