package com.craftbay.crafts.repository;

import com.craftbay.crafts.entity.cart.Cart;
import com.craftbay.crafts.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUserAndIsOrdered(User user, boolean isOrdered);
    Optional<Cart> findByIdAndIsOrdered(int id, boolean isOrdered);
}
