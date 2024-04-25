package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.order.OrderResponseDto;
import com.craftbay.crafts.dto.order.PlaceOrderResponseDto;
import com.craftbay.crafts.entity.cart.Cart;
import com.craftbay.crafts.entity.cart.CartItem;
import com.craftbay.crafts.entity.order.Order;
import com.craftbay.crafts.entity.paymentmethod.PaymentMethod;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import com.craftbay.crafts.entity.user.User;
import com.craftbay.crafts.repository.*;
import com.craftbay.crafts.service.OrderService;
import com.craftbay.crafts.service.StripeService;
import com.craftbay.crafts.service.UserService;
import com.craftbay.crafts.util.OrderUtil;
import com.craftbay.crafts.util.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
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

    @Autowired
    private StripeService stripeService;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PlaceOrderResponseDto placeOrder(int userId, int cartId) throws Exception {
        Optional<Cart> optionalUserCart = cartRepository.findById(cartId);
        if (optionalUserCart.isPresent()) {
            Cart userCart = optionalUserCart.get();
            Order newOrder = new Order();
            newOrder.setCart(userCart);
            newOrder.setOrderCreated(LocalDateTime.now());
            newOrder.setOrderStatus(OrderStatusEnum.ORDERED);
            newOrder.setUser(userCart.getUser());

            double totalOrderValue = 0;
            for (int i=0;i<userCart.getCartItems().size();i++){
                CartItem item = userCart.getCartItems().get(i);
                LocalDate today = LocalDate.now();
                ProductSellingPriceDetails sellingPriceDetails = item.getProduct().getProductSellingPriceDetails().stream()
                        .filter(obj -> ((obj.getDate().isBefore(today))||(obj.getDate().isEqual(today))))
                        .max(Comparator.comparing(ProductSellingPriceDetails::getDate))
                        .orElse(null);
                totalOrderValue = totalOrderValue + item.getQuantity()*sellingPriceDetails.getPrice();
            }
            newOrder.setTotalOrderValue(totalOrderValue + 500);

            Order savedOrder = orderRepository.save(newOrder);

            userCart.getCartItems().stream().forEach(item -> {

                LocalDate today = LocalDate.now();
                ProductSellingPriceDetails sellingPriceDetails = item.getProduct().getProductSellingPriceDetails().stream()
                        .filter(obj -> ((obj.getDate().isBefore(today))||(obj.getDate().isEqual(today))))
                        .max(Comparator.comparing(ProductSellingPriceDetails::getDate))
                        .orElse(null);

                item.getProduct().setRemainingQuantity(item.getProduct().getRemainingQuantity() - item.getQuantity());
                productRepository.save(item.getProduct());
            });

            userCart.setOrdered(true);
            cartRepository.save(userCart);

            String orderPaymentDetailsId = placeAnOrderWithStripe(optionalUserCart.get(), savedOrder);
            savedOrder.setOrderPaymentDetailsId(orderPaymentDetailsId);
            Order finalOrder = orderRepository.save(savedOrder);
            PlaceOrderResponseDto response = new PlaceOrderResponseDto(finalOrder.getId(),finalOrder.getTotalOrderValue(),finalOrder.getUser().getUsername());
            return response;
        } else {
            throw new Exception("User Doesn't Have a Cart!");
        }
    }

    private String placeAnOrderWithStripe(Cart cart, Order savedOrder) {

        PaymentMethod userPaymentMethod = paymentMethodRepository.findByUser(cart.getUser());

        String orderPaymentDetailsId = stripeService.placeAnOrderWithStripe(userPaymentMethod.getStripeCustomerId(), (long) savedOrder.getTotalOrderValue()*100, savedOrder.getId());
        return orderPaymentDetailsId;
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
