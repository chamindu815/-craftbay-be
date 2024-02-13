package com.craftbay.crafts.dto.product;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;

    @Lob
    private String image;
    public int getId()
    {
        return id;
    }


}
