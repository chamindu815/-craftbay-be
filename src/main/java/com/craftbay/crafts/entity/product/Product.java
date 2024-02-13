package com.craftbay.crafts.entity.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_tbl")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    @Lob
    private String image;

    private List<ProductPriceDetails> productPriceDetails;


//    public int getId()
//    {
//        return id;
//    }
}
