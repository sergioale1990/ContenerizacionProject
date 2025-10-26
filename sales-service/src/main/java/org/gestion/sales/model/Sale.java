package org.gestion.sales.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;

    private Long userId;

    private LocalDateTime date;

    private Double total;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SaleDetail> saleDetail;
}
