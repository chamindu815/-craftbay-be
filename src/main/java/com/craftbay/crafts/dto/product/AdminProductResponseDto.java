package com.craftbay.crafts.dto.product;

import lombok.Data;

import javax.persistence.Lob;
import java.util.List;

@Data
public class AdminProductResponseDto {
    private int id;
    private String name;
    private String description;
    private String category;
    private int remainingQuantity;

    private List<AdminProductBuyingPriceDetailsDto> adminProductBuyingPriceDetailsDtos;
    private List<AdminProductSellingPriceDetailsDto> adminProductSellingPriceDetailsDtos;

    @Lob
    private String image;
    public int getId()
    {
        return id;
    }


}
