package org.gestion.products.service;


import lombok.RequiredArgsConstructor;
import org.gestion.products.model.Category;
import org.gestion.products.model.Product;
import org.gestion.products.repository.CategoryRepository;
import org.gestion.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product createProduct(Product product, Long categoryId) {
        if (productRepository.findByCode(product.getCode()).isPresent()) {
            throw new RuntimeException("El producto ya existe");
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
