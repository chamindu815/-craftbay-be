package com.craftbay.crafts.controller;
import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.entity.Order;
import com.craftbay.crafts.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public OrderResponseDto addOrder (@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @GetMapping("/order")
    public List<OrderResponseDto> findAllOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/order/{id}")
    public OrderResponseDto findOrderById(int id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/orderbyname/{name}")
    public OrderResponseDto findOrderByName(String name){
        return orderService.getOrderByName(name);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable int id){
        return orderService.deleteOrder(id);
    }


}
