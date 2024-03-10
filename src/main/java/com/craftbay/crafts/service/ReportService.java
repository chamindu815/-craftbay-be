package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.report.FileExportResponseDto;
import com.craftbay.crafts.dto.report.InventoryReportResponse;
import com.craftbay.crafts.dto.report.SalesReportResponse;
import com.craftbay.crafts.entity.product.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    FileExportResponseDto getInventoryReport();

    FileExportResponseDto getLastMonthSalesReport() throws Exception;

    List<InventoryReportResponse> getInventoryReportData();

    List<SalesReportResponse> getSalesReport(LocalDate startDate, LocalDate endDate);
}
