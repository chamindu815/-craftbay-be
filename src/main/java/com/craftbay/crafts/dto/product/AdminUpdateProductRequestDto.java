package com.craftbay.crafts.dto.product;

import lombok.Data;

import javax.persistence.Lob;
import java.util.List;

@Data
public class AdminUpdateProductRequestDto {
    private int id;
    private String name;
    private String description;
    private String category;

    @Lob
    private String image;

    private List<AdminProductBuyingPriceDetailsDto> adminProductBuyingPriceDetailsDtoList;
    private List<AdminProductSellingPriceDetailsDto> adminProductSellingPriceDetailsDtoList;
}