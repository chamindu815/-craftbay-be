package com.craftbay.crafts.repository;

import com.craftbay.crafts.entity.paymentmethod.PaymentMethod;
import com.craftbay.crafts.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Integer> {

    PaymentMethod findByUser(User user);
}
