package com.craftbay.crafts.service;

import com.craftbay.crafts.dto.report.FileExportResponseDto;
import com.craftbay.crafts.entity.product.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ReportService {

    FileExportResponseDto getInventoryReport();

    FileExportResponseDto getLastMonthSalesReport() throws Exception;
}
