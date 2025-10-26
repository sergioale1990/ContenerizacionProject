package org.gestion.sales.controller;


import lombok.RequiredArgsConstructor;
import org.gestion.sales.model.Sale;
import org.gestion.sales.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    @PreAuthorize("hasAuthority('access.sales')")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return ResponseEntity.ok(saleService.createSale(sale));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('access.sales')")
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('access.sales')")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }
}
