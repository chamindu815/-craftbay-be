package com.craftbay.crafts.dto.product;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;

    @Lob
    private String image;
}
