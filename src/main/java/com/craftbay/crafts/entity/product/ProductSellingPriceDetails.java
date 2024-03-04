package com.craftbay.crafts.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_selling_price_tbl")
public class ProductSellingPriceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_selling_price_generator")
    private int id;
    private LocalDate date;
    private double price;
}
