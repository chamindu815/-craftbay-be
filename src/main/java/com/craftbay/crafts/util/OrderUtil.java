package com.craftbay.crafts.util;


import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.entity.order.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUtil {
    public static OrderResponseDto convertOrderToOrderResponseDto(Order order) {
        OrderResponseDto response = new OrderResponseDto();
        response.setId(order.getId());
        response.setOrderStatus(order.getOrderStatus());
        response.setOrderCreated(order.getOrderCreated());
        response.setCart(CartUtil.covertCartToCartResponseDto(order.getCart()));
        response.setUser(UserUtil.convertUserToUserResponseDto(order.getUser()));
        response.setTotalOrderValue(order.getTotalOrderValue());
        return response;
    }

    public static List<OrderResponseDto> convertOrderListToOrderResponseDtoList(List<Order> orders) {
        List<OrderResponseDto> responseList = new ArrayList<>();
        for (int i=0;i<orders.size();i++) {
            OrderResponseDto order = convertOrderToOrderResponseDto(orders.get(i));
            responseList.add(order);
        }
        return responseList;
    }

}
