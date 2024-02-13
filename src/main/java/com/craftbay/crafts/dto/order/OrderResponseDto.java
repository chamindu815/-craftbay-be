package com.craftbay.crafts.dto.order;

import lombok.Data;

@Data
public class OrderResponseDto {
    private int id;
    private String name;
    private String address;
    private double price;
    private int quantity;
}
