package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.entity.Order;
import com.craftbay.crafts.repository.OrderRepository;
import com.craftbay.crafts.service.OrderService;
import com.craftbay.crafts.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderIMPL implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDto saveOrder(Order order){
        OrderResponseDto response = OrderUtil.convertOrderToOrderResponseDto(orderRepository.save(order));
        return response;
    }

    public List<OrderResponseDto> getOrders(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderResponseDto> responseList = new ArrayList<>();
        for (int i=0;i<orderList.size(); i++){

            OrderResponseDto response = OrderUtil.convertOrderToOrderResponseDto(orderList.get(i));
            responseList.add(response);
        }
        return responseList;
    }

    public OrderResponseDto getOrderById(int id){
        OrderResponseDto response = OrderUtil.convertOrderToOrderResponseDto(orderRepository.findById(id).get());
        return response;
    }

    public OrderResponseDto getOrderByName(String name){
        OrderResponseDto response = OrderUtil.convertOrderToOrderResponseDto(orderRepository.findByName(name));
        return response;
    }


    public String deleteOrder(int id){
        orderRepository.deleteById(id);
        return "Order Closed ||" + id;
    }

}
