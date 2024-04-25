package com.craftbay.crafts.controller;

import com.craftbay.crafts.dto.report.FileExportResponseDto;
import com.craftbay.crafts.dto.report.InventoryReportResponse;
import com.craftbay.crafts.dto.report.SalesReportResponse;
import com.craftbay.crafts.service.ReportService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

//    @CrossOrigin
//    @GetMapping("/inventory")
//    public ResponseEntity<InputStreamResource> exportReport() throws Exception {
//
//        FileExportResponseDto responseDto =
//                reportService.getInventoryReport();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, responseDto.getFileName())
//                .contentType(responseDto.getMediaType())
//                .body(responseDto.getResource());
//    }
//
//    @CrossOrigin
//    @GetMapping("/sales")
//    public ResponseEntity<InputStreamResource> getSalesReport() throws Exception {
//
//        FileExportResponseDto responseDto =
//                reportService.getLastMonthSalesReport();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, responseDto.getFileName())
//                .contentType(responseDto.getMediaType())
//                .body(responseDto.getResource());
//    }

    @CrossOrigin
    @GetMapping("/sales-report")
    public List<SalesReportResponse> getSalesReport(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate) {
        return reportService.getSalesReport(LocalDate.parse(startDate),LocalDate.parse(endDate));
    }

    @CrossOrigin
    @GetMapping("/inventory-report")
    public List<InventoryReportResponse> getInventoryReport() {
        return reportService.getInventoryReportData();
    }

}
