package org.gestion.products.controller;


import lombok.RequiredArgsConstructor;
import org.gestion.products.model.Category;
import org.gestion.products.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('access.products')")
    public ResponseEntity<Category> createCategory(@RequestBody Category category, @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        return ResponseEntity.ok(categoryService.createCategory(category, token));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('access.products')")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('access.products')")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('access.products')")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
