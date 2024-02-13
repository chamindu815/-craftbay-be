package com.craftbay.crafts.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_price_tbl")
public class ProductPriceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_price_generator")
    private long id;

    private LocalDate date;

    private double price;

    private int quantity;
}
