package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.order.OrderResponseDto;

import java.util.List;

public interface OrderService {
    public String placeOrder(int userId, int cartId) throws Exception;
    public String cancelOrder(int userId,int orderId) throws Exception;
    public List<OrderResponseDto> viewMyOrders(int userId) throws Exception;

    OrderResponseDto viewOrderById(int userId, int orderId) throws Exception;
}
