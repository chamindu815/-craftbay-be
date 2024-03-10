package com.craftbay.crafts.dto.order;

import com.craftbay.crafts.dto.cart.response.CartResponseDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.util.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDto {
    private int id;
    private LocalDateTime orderCreated;
    private OrderStatusEnum orderStatus;
    private CartResponseDto cart;
    private UserResponseDto user;
    private double totalOrderValue;
}
