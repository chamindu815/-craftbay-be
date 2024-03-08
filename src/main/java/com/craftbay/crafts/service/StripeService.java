package com.craftbay.crafts.service;

import com.stripe.exception.StripeException;

public interface StripeService {
    public String createStripeUser(String firstName, String lastName, String userName) throws StripeException;
    public String addCardToStripeUser(String stripeCustomerId);
    public String placeAnOrderWithStripe(String stripeCustomerId, long orderAmount, int orderId);
    public void updateStripeCustomer(String stripeCustomerId,String firstName, String lastName);
}
