package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.entity.cart.Cart;
import com.craftbay.crafts.entity.order.Order;
import com.craftbay.crafts.entity.user.User;
import com.craftbay.crafts.repository.CartRepository;
import com.craftbay.crafts.repository.OrderRepository;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.repository.UserRepository;
import com.craftbay.crafts.service.OrderService;
import com.craftbay.crafts.service.UserService;
import com.craftbay.crafts.util.OrderUtil;
import com.craftbay.crafts.util.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String placeOrder(int userId, int cartId) throws Exception {
        Optional<Cart> optionalUserCart = cartRepository.findById(cartId);
        if (optionalUserCart.isPresent()) {
            Cart userCart = optionalUserCart.get();
            Order newOrder = new Order();
            newOrder.setCart(userCart);
            newOrder.setOrderCreated(LocalDateTime.now());
            newOrder.setOrderStatus(OrderStatusEnum.ORDERED);
            newOrder.setUser(userCart.getUser());
            orderRepository.save(newOrder);

            userCart.getCartItems().stream().forEach(item -> {
                item.getProduct().setRemainingQuantity(item.getProduct().getRemainingQuantity() - item.getQuantity());
                productRepository.save(item.getProduct());
            });

            userCart.setOrdered(true);
            cartRepository.save(userCart);
        } else {
            throw new Exception("User Doesn't Have a Cart!");
        }

        return null;
    }

    @Override
    public String cancelOrder(int userId, int orderId) throws Exception {
        Optional<Order> optionalExistingOrder = orderRepository.findById(orderId);
        if (optionalExistingOrder.isPresent()) {
            Order existingOrder = optionalExistingOrder.get();
            existingOrder.setOrderStatus(OrderStatusEnum.CANCELLED);
            orderRepository.save(existingOrder);

            existingOrder.getCart().getCartItems().stream().forEach(item -> {
                item.getProduct().setRemainingQuantity(item.getProduct().getRemainingQuantity() + item.getQuantity());
                productRepository.save(item.getProduct());
            });
        } else {
            throw new Exception("No Order Found!");
        }
        return null;
    }

    public List<OrderResponseDto> viewMyOrders(int userId) throws Exception {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Order> userOrders = orderRepository.findAllByUser(user);
            return OrderUtil.convertOrderListToOrderResponseDtoList(userOrders);
        } else {
            throw new Exception("User Not Found!");
        }
    }

    @Override
    public OrderResponseDto viewOrderById(int userId, int orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            return OrderUtil.convertOrderToOrderResponseDto(optionalOrder.get());
        } else {
            throw new Exception("Order Not Found!");
        }
    }
}
