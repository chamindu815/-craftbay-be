package com.craftbay.crafts.dto.order;

import lombok.Data;

@Data
public class OrderRequestDto {

    private String name;
    private String address;
    private double price;
    private int quantity;
}
