package com.product.service;

import java.util.List;

import com.product.entity.CitizenPlan;
import com.product.entity.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatuses();

	public List<CitizenPlan> getCitizenPlans(SearchRequest request);

	public void exportExcel(HttpServletResponse response) throws Exception;

	public void exportPdf(HttpServletResponse response) throws Exception;

}
