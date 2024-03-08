package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.entity.order.Order;
import com.craftbay.crafts.repository.OrderRepository;
import com.craftbay.crafts.service.AdminOrderService;
import com.craftbay.crafts.util.OrderUtil;
import com.craftbay.crafts.util.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void adminUpdateOrder(int orderId, OrderStatusEnum orderStatus) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus(orderStatus);
            orderRepository.save(order);
        } else {
            throw new Exception("Order Not Found!");
        }
    }

    public List<OrderResponseDto> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        return OrderUtil.convertOrderListToOrderResponseDtoList(orders);
    }

    @Override
    public OrderResponseDto getOrderById(int orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            return OrderUtil.convertOrderToOrderResponseDto(optionalOrder.get());
        } else {
            throw new Exception("Order Not Found!");
        }
    }

    @Override
    public List<OrderResponseDto> getAllOrderByStatus(OrderStatusEnum status) {
        List<Order> ordersByStatus = orderRepository.findByOrderStatus(status);
        return OrderUtil.convertOrderListToOrderResponseDtoList(ordersByStatus);
    }
}
