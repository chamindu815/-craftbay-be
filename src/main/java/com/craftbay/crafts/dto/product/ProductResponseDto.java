package com.craftbay.crafts.dto.product;

import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.List;

@Data
public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private String category;
    private int remainingQuantity;

    private List<ProductBuyingPriceDetailsDto> productBuyingPriceDetailsDtos;
    private List<ProductSellingPriceDetailsDto> productSellingPriceDetailsDtos;

    @Lob
    private String image;
    public int getId()
    {
        return id;
    }


}
