package com.craftbay.crafts.repository;

import com.craftbay.crafts.entity.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
}
