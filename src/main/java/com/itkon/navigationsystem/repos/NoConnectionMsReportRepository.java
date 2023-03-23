package com.itkon.navigationsystem.repos;

import com.itkon.navigationsystem.model.NoConnectionMsReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoConnectionMsReportRepository extends JpaRepository<NoConnectionMsReport, String> {

    List<NoConnectionMsReport> findAllByMobileStation_Id(String mobileStationId);
}
