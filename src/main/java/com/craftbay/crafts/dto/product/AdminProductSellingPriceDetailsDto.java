package com.craftbay.crafts.dto.product;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminProductSellingPriceDetailsDto {
    private long id;
    private LocalDate date;
    private double price;

}
