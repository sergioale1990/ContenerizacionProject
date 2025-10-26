package org.gestion.products.service;


import lombok.RequiredArgsConstructor;
import org.gestion.products.model.Category;
import org.gestion.products.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final LogSenderService logSenderService;

    public Category createCategory(Category category, String token) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new RuntimeException("La categoria ya existe");
        }
        logSenderService.sendLog("admin", "productos", "Crear categoria", "Se creo Categoria" + category.getName(), token);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
