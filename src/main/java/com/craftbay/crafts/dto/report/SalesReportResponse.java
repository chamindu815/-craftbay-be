package com.craftbay.crafts.dto.report;

import com.craftbay.crafts.util.enums.ProductCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesReportResponse {
    private String productName;
    private ProductCategoryEnum category;
    private int sellQuantity;
    private double avgSellPrice;
    private double TotalSales;
}
