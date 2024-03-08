package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerListPaymentMethodsParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    public String createStripeUser(String firstName, String lastName, String userName) throws StripeException {

        CustomerCreateParams params =
                CustomerCreateParams.builder()
                        .setName(firstName + " " + lastName)
                        .setEmail(userName)
                        .build();
        Customer customer = Customer.create(params);
        return customer.getId();

    }

    @Override
    public String addCardToStripeUser(String stripeCustomerId) {
        try {
            Customer customer = Customer.retrieve(stripeCustomerId);
            CustomerUpdateParams cardParams =
                    CustomerUpdateParams.builder()
                            .setSource("tok_visa")
                            .build();
            customer = customer.update(cardParams);
            Customer resource = Customer.retrieve(stripeCustomerId);
            CustomerListPaymentMethodsParams params =
                    CustomerListPaymentMethodsParams.builder().setLimit(3L).build();
            PaymentMethodCollection paymentMethods = resource.listPaymentMethods(params);
            return paymentMethods.getData().get(0).getId();
        } catch (Exception e) {
            System.out.println("Error while adding card to user!");
        }
        return null;
    }

    @Override
    public String placeAnOrderWithStripe(String stripeCustomerId, long orderAmount, int orderId) {
        try {
            Customer resource = Customer.retrieve(stripeCustomerId);
            CustomerListPaymentMethodsParams params =
                    CustomerListPaymentMethodsParams.builder().setLimit(3L).build();
            PaymentMethodCollection paymentMethods = resource.listPaymentMethods(params);

            PaymentIntentCreateParams paymentIntentParams = PaymentIntentCreateParams.builder()
                    .setCustomer(stripeCustomerId)
                    .setPaymentMethod(paymentMethods.getData().get(0).getId())
                    .setAmount(orderAmount)
                    .setCurrency("lkr")
                    .setDescription("OrderID: " + orderId)
                    .setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER).setEnabled(true).build())
                    .setConfirm(true)
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);
            return paymentIntent.getId();
        } catch (Exception e) {
            System.out.println("Error while doing a stripe payment!!!");
        }
        return null;
    }

    @Override
    public void updateStripeCustomer(String stripeCustomerId, String firstName, String lastName) {
        try {
            Customer resource = Customer.retrieve(stripeCustomerId);
            CustomerUpdateParams params =
                    CustomerUpdateParams.builder().setName(firstName + " " + lastName).build();
            Customer customer = resource.update(params);
        } catch (Exception e) {
            System.out.println("Exception occured while updating stripe customer!");
        }

    }
}
