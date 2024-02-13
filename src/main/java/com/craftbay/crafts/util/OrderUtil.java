package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderUtil {

    public static OrderResponseDto convertOrderToOrderResponseDto(Order order){
        OrderResponseDto response = new OrderResponseDto();
        response.setId(order.getId());
        response.setName(order.getName());
        response.setAddress(order.getAddress());
        response.setQuantity(order.getQuantity());
//        response.setPrice(order.getPrice());
        return response;
    }
}
