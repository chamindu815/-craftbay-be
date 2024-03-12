package com.craftbay.crafts.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderResponseDto {
    private int orderId;
    private double orderValue;
    private String email;
}
