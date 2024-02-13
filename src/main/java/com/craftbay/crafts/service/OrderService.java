package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.entity.Order;

import java.util.List;

public interface OrderService {


    OrderResponseDto saveOrder(Order order);

    List<OrderResponseDto> getOrders();

    OrderResponseDto getOrderById(int id);

    OrderResponseDto getOrderByName(String name);

    String deleteOrder(int id);
}
