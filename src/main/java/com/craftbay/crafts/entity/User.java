package com.craftbay.crafts.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @Column(name = "id", length =45 )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", length =255 )
    private String name;

    @Column(name = "user_email", length =255 )
    private String email;

    @Column(name = "user_phone", length =255 )
    private String phone;

    @Column(name = "user_password", length =255 )
    private String password;

}
