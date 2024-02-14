package com.craftbay.crafts.dto.product;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductSellingPriceDetailsDto {
    private long id;
    private LocalDate date;
    private double price;

}
