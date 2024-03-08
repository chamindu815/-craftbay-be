package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.card.AddCardDetailsRequest;
import com.craftbay.crafts.dto.register.RegisterRequestDto;
import com.craftbay.crafts.dto.user.UserResponseDto;
import com.craftbay.crafts.entity.paymentmethod.PaymentMethod;
import com.craftbay.crafts.entity.user.User;
import com.craftbay.crafts.repository.PaymentMethodRepository;
import com.craftbay.crafts.repository.UserRepository;
import com.craftbay.crafts.service.StripeService;
import com.craftbay.crafts.service.UserService;
import com.craftbay.crafts.util.ProductUtil;
import com.craftbay.crafts.util.UserUtil;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.param.CustomerListPaymentMethodsParams;
import com.stripe.param.CustomerUpdateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StripeService stripeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public String registerUser(RegisterRequestDto request) {
        User user = convertUserRegisterRequestDtoToUser(request);
        User savedUser = userRepository.save(user);
        try {
            String stripeCustomerId = stripeService.createStripeUser(request.getFirstName(),request.getLastName(), request.getUsername());
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setUser(savedUser);
            paymentMethod.setStripeCustomerId(stripeCustomerId);
            paymentMethodRepository.save(paymentMethod);
        } catch (Exception e) {
            System.out.println("Error while creating stripe user");
        }
        return savedUser.getUsername();
    }

    public User convertUserRegisterRequestDtoToUser(RegisterRequestDto request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserResponseDto getUserById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        UserResponseDto userResponseDto = UserUtil.convertUserToUserResponseDto(user);
        return userResponseDto;
    }

    @Override
    public void addCardToUser(int userId, AddCardDetailsRequest request) {
        Optional<User> optionalUser = userRepository.findById(userId);
        PaymentMethod paymentMethod = paymentMethodRepository.findByUser(optionalUser.get());
        paymentMethod.setCardNo(request.getCardNo());
        paymentMethod.setMonth(request.getMonth());
        paymentMethod.setYear(request.getYear());
        paymentMethod.setCvv(request.getCvv());
        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        String stripePaymentMethodId = stripeService.addCardToStripeUser(paymentMethod.getStripeCustomerId());
        savedPaymentMethod.setPaymentMethodId(stripePaymentMethodId);
        paymentMethodRepository.save(savedPaymentMethod);
    }
}
