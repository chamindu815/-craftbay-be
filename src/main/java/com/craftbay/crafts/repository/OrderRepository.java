package com.craftbay.crafts.repository;

import com.craftbay.crafts.entity.order.Order;
import com.craftbay.crafts.entity.user.User;
import com.craftbay.crafts.util.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByUser(User user);

    List<Order> findByOrderStatus(OrderStatusEnum status);

    List<Order> findAllByOrderStatusAndOrderCreatedBetweenOrderByOrderCreated(OrderStatusEnum orderStatus, LocalDateTime startDate, LocalDateTime endDate);
}
