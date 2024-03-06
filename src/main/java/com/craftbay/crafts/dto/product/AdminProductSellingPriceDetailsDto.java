package com.craftbay.crafts.dto.product;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminProductSellingPriceDetailsDto {
    private int id;
    private LocalDate date;
    private double price;

}
