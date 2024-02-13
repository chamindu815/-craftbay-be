package com.craftbay.crafts.repository;

import com.craftbay.crafts.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Order findByName(String name);
}
