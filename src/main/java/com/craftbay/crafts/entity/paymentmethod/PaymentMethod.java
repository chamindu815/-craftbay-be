package com.craftbay.crafts.entity.paymentmethod;


import com.craftbay.crafts.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String stripeCustomerId;

    private String paymentMethodId;

    private String cardNo;

    private String month;

    private String year;

    private String cvv;
}
