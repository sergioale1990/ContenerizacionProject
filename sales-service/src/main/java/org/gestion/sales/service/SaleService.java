package org.gestion.sales.service;


import lombok.RequiredArgsConstructor;
import org.gestion.sales.model.Sale;
import org.gestion.sales.model.SaleDetail;
import org.gestion.sales.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public Sale createSale(Sale sale) {
        double total = 0.0;
        for(SaleDetail saleDetail : sale.getSaleDetail()) {
            double subTotal = saleDetail.getUnitaryPrice() * saleDetail.getQuantity();
            saleDetail.setSubtotal(subTotal);
            saleDetail.setSale(sale);
            total += subTotal;
        }
        sale.setDate(LocalDateTime.now());
        sale.setTotal(total);

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }
}
