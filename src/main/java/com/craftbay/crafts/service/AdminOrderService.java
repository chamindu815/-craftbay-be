package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.util.enums.OrderStatusEnum;

import java.util.List;

public interface AdminOrderService {

    public void adminUpdateOrder(int orderId,OrderStatusEnum orderStatus) throws Exception;

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(int orderId) throws Exception;

    List<OrderResponseDto> getAllOrderByStatus(OrderStatusEnum status);
}
