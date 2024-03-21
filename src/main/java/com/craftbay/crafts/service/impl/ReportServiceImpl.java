package com.craftbay.crafts.service.impl;

import com.craftbay.crafts.dto.report.FileExportResponseDto;
import com.craftbay.crafts.dto.report.InventoryReportResponse;
import com.craftbay.crafts.dto.report.SalesReportResponse;
import com.craftbay.crafts.entity.cart.CartItem;
import com.craftbay.crafts.entity.order.Order;
import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.entity.product.ProductBuyingPriceDetails;
import com.craftbay.crafts.entity.product.ProductSellingPriceDetails;
import com.craftbay.crafts.entity.report.Report;
import com.craftbay.crafts.repository.OrderRepository;
import com.craftbay.crafts.repository.ProductRepository;
import com.craftbay.crafts.repository.ReportRepository;
import com.craftbay.crafts.service.ReportService;
import com.craftbay.crafts.util.enums.OrderStatusEnum;
import com.craftbay.crafts.util.enums.ReportType;
import com.craftbay.crafts.util.exporter.CSVHelper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CSVHelper csvHelper;

    @Override
    public FileExportResponseDto getInventoryReport() {
        List<Product> productList = productRepository.findAll();

        ByteArrayInputStream byteArrayInputStream = csvHelper.inventoryReportExporter(productList);
        return FileExportResponseDto.builder()
                .fileName(
                        "inventory_report.csv")
                .resource(new InputStreamResource(byteArrayInputStream))
                .mediaType(MediaType.parseMediaType("application/csv"))
                .build();
    }


    public FileExportResponseDto getLastMonthSalesReport() throws Exception {


        final String[] HEADERS = {
                "Product Name",
                "Product Type",
                "Selling Quantity",
                "Avg Selling Price",
                "Total Sales"
        };
        final CSVFormat format = CSVFormat.DEFAULT.withHeader(HEADERS);

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);

            LocalDateTime endDateTime = LocalDateTime.now();

            LocalDate startDate = LocalDate.now().minusMonths(1);
            LocalTime localTime = LocalTime.MIDNIGHT;
            LocalDateTime startDateTime = LocalDateTime.of(startDate, localTime);

            List<Order> orders = orderRepository.findAllByOrderStatusAndOrderCreatedBetweenOrderByOrderCreated(OrderStatusEnum.COMPLETED, startDateTime, endDateTime);
            Map<String, Integer> map = new HashMap<>();

            Map<Product, Integer> productQuantityMap = new HashMap<>();
            Map<Product, Double> productPriceMap = new HashMap<>();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                for (int j = 0; j < order.getCart().getCartItems().size(); j++) {
                    CartItem item = order.getCart().getCartItems().get(j);


                    LocalDate orderDate = order.getOrderCreated().toLocalDate();
                    ProductSellingPriceDetails productSellingPriceDetails = item.getProduct().getProductSellingPriceDetails().stream()
                            .filter(obj -> ((obj.getDate().isBefore(orderDate)) || (obj.getDate().isEqual(orderDate))))
                            .max(Comparator.comparing(ProductSellingPriceDetails::getDate))
                            .orElse(null);


                    if (productQuantityMap.get(item.getProduct()) != null) {
                        double avgPrice = ((productQuantityMap.get(item.getProduct()) * productPriceMap.get(item.getProduct())) + (productSellingPriceDetails.getPrice() * item.getQuantity())) / (productQuantityMap.get(item.getProduct()) + item.getQuantity());
                        productQuantityMap.put(item.getProduct(), productQuantityMap.get(item.getProduct()) + item.getQuantity());
                        productPriceMap.put(item.getProduct(), avgPrice);
                    } else {
                        productQuantityMap.put(item.getProduct(), item.getQuantity());
                        productPriceMap.put(item.getProduct(), productSellingPriceDetails.getPrice());
                    }
                }

            }

            for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
                List<String> data = new ArrayList<>();
                data.add(entry.getKey().getName());
                data.add(entry.getKey().getCategory().toString());
                data.add(String.valueOf(entry.getValue()));
                data.add(String.valueOf(productPriceMap.get(entry.getKey())));
                data.add(String.valueOf(productPriceMap.get(entry.getKey()) * entry.getValue()));
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
            return FileExportResponseDto.builder()
                    .fileName(
                            "Sales_report.csv")
                    .resource(new InputStreamResource(byteArrayInputStream))
                    .mediaType(MediaType.parseMediaType("application/csv"))
                    .build();
        } catch (Exception e) {
            throw new Exception("Cannot get last month sales report now!");
        }
    }

    @Override
    public List<InventoryReportResponse> getInventoryReportData() {

        List<InventoryReportResponse> inventoryReport = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
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

            InventoryReportResponse response = new InventoryReportResponse();
            response.setProductName(product.getName());
            response.setCategory(product.getCategory());
            response.setRemainingQuantity(product.getRemainingQuantity());
            response.setBuyingPrice(buyingPriceDetails.getPrice());
            response.setSellingPrice(sellingPriceDetails.getPrice());
            inventoryReport.add(response);
        }

        Report report = new Report();
        report.setReportType(ReportType.INVENTORY_REPORT);
        report.setGeneratedDate(LocalDateTime.now());
        String title = "Inventory_Report_" + LocalDate.now();
        report.setTitle(title);
        reportRepository.save(report);

        return inventoryReport;
    }

    public List<SalesReportResponse> getSalesReport(LocalDate startDate, LocalDate endDate) {

        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDateTime startDateTime = LocalDateTime.of(startDate, localTime);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, localTime);

        List<Order> orders = orderRepository.findAllByOrderStatusAndOrderCreatedBetweenOrderByOrderCreated(OrderStatusEnum.COMPLETED, startDateTime, endDateTime);
        Map<String, Integer> map = new HashMap<>();

        Map<Product, Integer> productQuantityMap = new HashMap<>();
        Map<Product, Double> productPriceMap = new HashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            for (int j = 0; j < order.getCart().getCartItems().size(); j++) {
                CartItem item = order.getCart().getCartItems().get(j);


                LocalDate orderDate = order.getOrderCreated().toLocalDate();
                ProductSellingPriceDetails productSellingPriceDetails = item.getProduct().getProductSellingPriceDetails().stream()
                        .filter(obj -> ((obj.getDate().isBefore(orderDate)) || (obj.getDate().isEqual(orderDate))))
                        .max(Comparator.comparing(ProductSellingPriceDetails::getDate))
                        .orElse(null);


                if (productQuantityMap.get(item.getProduct()) != null) {
                    double avgPrice = ((productQuantityMap.get(item.getProduct()) * productPriceMap.get(item.getProduct())) + (productSellingPriceDetails.getPrice() * item.getQuantity())) / (productQuantityMap.get(item.getProduct()) + item.getQuantity());
                    productQuantityMap.put(item.getProduct(), productQuantityMap.get(item.getProduct()) + item.getQuantity());
                    productPriceMap.put(item.getProduct(), avgPrice);
                } else {
                    productQuantityMap.put(item.getProduct(), item.getQuantity());
                    productPriceMap.put(item.getProduct(), productSellingPriceDetails.getPrice());
                }
            }

        }

        List<SalesReportResponse> salesReportResponse = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
            SalesReportResponse response = new SalesReportResponse();
            response.setProductName(entry.getKey().getName());
            response.setCategory(entry.getKey().getCategory());
            response.setSellQuantity(entry.getValue());
            response.setAvgSellPrice(productPriceMap.get(entry.getKey()));
            response.setTotalSales(productPriceMap.get(entry.getKey()) * entry.getValue());
            salesReportResponse.add(response);
        }

        Report report = new Report();
        report.setReportType(ReportType.SALES_REPORT);
        report.setGeneratedDate(LocalDateTime.now());
        String title = "Sales_Report_" + startDate + "-" + endDate;
        report.setTitle(title);
        reportRepository.save(report);

        return salesReportResponse;
    }

}
