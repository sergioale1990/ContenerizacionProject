package org.gestion.products.controller;


import lombok.RequiredArgsConstructor;
import org.gestion.products.model.Product;
import org.gestion.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyAuthority('access.products')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.createProduct(product, categoryId));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('access.products')")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('access.products')")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('access.products')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
