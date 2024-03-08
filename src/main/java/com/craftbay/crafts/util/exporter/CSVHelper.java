package com.craftbay.crafts.util.exporter;

import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

@Component
public class CSVHelper {
    public ByteArrayInputStream inventoryReportExporter(
            List<Product> productList) {

        final String[] HEADERS = {
                "ProductId",
                "Product Name",
                "Product Type",
                "Remaining Quantity",
                "Buying Price",
                "Selling Price"
        };
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(HEADERS);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);

//            Page<TimeEntryReportViewData> reportData = reportViewResponseDto.getReportData();
//            List<TimeEntryReportViewData> viewResponseDtoList =
//                    reportData.get().collect(Collectors.toList());
            for (Product product : productList) {

                LocalDate today = LocalDate.now();
                ProductBuyingPriceDetails buyingPriceDetails = product.getProductBuyingPriceDetails().stream()
                        .filter(obj -> ((obj.getDate().isBefore(today)) || (obj.getDate().isEqual(today))))
                        .max(Comparator.comparing(ProductBuyingPriceDetails::getDate))
                        .orElse(null);
                ProductSellingPriceDetails sellingPriceDetails = product.getProductSellingPriceDetails().stream()
                        .filter(obj -> ((obj.getDate().isBefore(today)) || (obj.getDate().isEqual(today))))
                        .max(Comparator.comparing(ProductSellingPriceDetails::getDate))
                        .orElse(null);

                List<String> data = new ArrayList<>();
                data.add(String.valueOf(product.getId()));
                data.add(product.getName());
                data.add(product.getCategory().toString());
                data.add(String.valueOf(product.getRemainingQuantity()));
                data.add(String.valueOf(buyingPriceDetails.getPrice()));
                data.add(String.valueOf(sellingPriceDetails.getPrice()));

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }


    public ByteArrayInputStream salesReportExporter(Map<Product, Integer> productQuantityMap) throws IOException {

        final String[] HEADERS = {
                "ProductId",
                "Product Name",
                "Product Type",
                "Quantity",
                "Sales Amount"
        };
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(HEADERS);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);

            for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
//                System.out.println("Key = " + entry.getKey() +
//                        ", Value = " + entry.getValue());

                List<String> data = new ArrayList<>();
                data.add(String.valueOf(entry.getKey().getId()));
                data.add(entry.getKey().getName());
                data.add(entry.getKey().getCategory().toString());
                data.add(String.valueOf(entry.getValue()));
                data.add(entry.getKey().getName());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
