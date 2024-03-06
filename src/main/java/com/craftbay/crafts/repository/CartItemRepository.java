package com.craftbay.crafts.repository;

import com.craftbay.crafts.entity.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository  extends JpaRepository<CartItem,Integer> {
}
