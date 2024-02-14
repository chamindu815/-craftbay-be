package com.craftbay.crafts.dto.product;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class ProductBuyingPriceDetailsDto {
    private long id;
    private LocalDate date;
    private double price;
    private int quantity;
}
