package com.craftbay.crafts.util;

import com.craftbay.crafts.dto.card.CardDetailsResponseDto;
import com.craftbay.crafts.entity.paymentmethod.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class CardUtil {

    public static CardDetailsResponseDto convertPaymentMethodToCardDetailsResponseDto(PaymentMethod paymentMethod) {
        CardDetailsResponseDto response = new CardDetailsResponseDto();
        response.setUser(UserUtil.convertUserToUserResponseDto(paymentMethod.getUser()));
        response.setCardNo(paymentMethod.getCardNo());
        response.setMonth(paymentMethod.getMonth());
        response.setYear(paymentMethod.getYear());
        response.setCvv(paymentMethod.getCvv());
        return response;
    }

}
