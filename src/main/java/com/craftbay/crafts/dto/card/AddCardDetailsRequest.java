package com.craftbay.crafts.dto.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCardDetailsRequest {
    private String cardNo;
    private String month;
    private String year;
    private String cvv;
}
